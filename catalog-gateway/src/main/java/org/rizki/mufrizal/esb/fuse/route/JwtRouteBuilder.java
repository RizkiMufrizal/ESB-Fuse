package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.rs.security.jose.jwa.SignatureAlgorithm;
import org.apache.cxf.rs.security.jose.jws.HmacJwsSignatureProvider;
import org.apache.cxf.rs.security.jose.jws.JwsJwtCompactProducer;
import org.apache.cxf.rs.security.jose.jwt.JwtClaims;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 13:10
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File JwtRouteBuilder
 */
public class JwtRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:generateJwt")
                .choice()
                .when(header("Authorization").isNull())
                    .to("direct:security-header-null")
                .when(header("Authorization").isNotNull())
                    .to("direct:security-header-not-null")
                    .to("direct-vm:execute-sql-jwt")
                    .log("ini adalah hasil : ${body}")
                    .choice()
                    .when(body().isNull())
                        .to("direct:security-header-null")
                    .when(body().isNotNull())
                    .process(exchange -> {

                        LinkedCaseInsensitiveMap<String> objectHashMap = (LinkedCaseInsensitiveMap<String>) exchange.getIn().getBody();
                        String username = objectHashMap.get("username");
                        String secret = objectHashMap.get("secret");
                        long expD = Long.parseLong(objectHashMap.get("exp"));

                        long iat = Instant.now().getEpochSecond();
                        long exp = Instant.now().plusSeconds(expD).getEpochSecond();

                        JwtClaims jwtClaims = new JwtClaims();
                        jwtClaims.setSubject(username);
                        jwtClaims.setExpiryTime(exp);
                        jwtClaims.setIssuedAt(iat);

                        JwsJwtCompactProducer jwsProducer = new JwsJwtCompactProducer(jwtClaims);
                        String token = jwsProducer.signWith(new HmacJwsSignatureProvider(Base64.getEncoder().encodeToString(secret.getBytes()), SignatureAlgorithm.HS256));

                        Map<String, Object> jwtResponse = new HashMap<>();
                        jwtResponse.put("Token", token);
                        jwtResponse.put("Username", username);
                        jwtResponse.put("Iat", iat);
                        jwtResponse.put("Exp", exp);

                        exchange.getOut().setBody(Response.status(Response.Status.OK).entity(jwtResponse).build());
                    }).end();

        from("direct:security-header-not-null")
                .process(exchange -> {
                    String authorizationHeader = (String) exchange.getIn().getHeader("Authorization");
                    String authorization = new String(Base64.getDecoder().decode(authorizationHeader.substring("Basic".length()).trim()));

                    String username = authorization.split(":")[0];
                    String password = authorization.split(":")[1];

                    Map<String, Object> objectMap = new HashMap<>();
                    objectMap.put("Username", username);
                    objectMap.put("Password", password);

                    exchange.getIn().setBody(objectMap);
                });

        from("direct:security-header-null")
                .process(exchange -> exchange.getOut().setBody(Response.status(Response.Status.UNAUTHORIZED).build()))
                .end();

        from("direct:jwt-interceptor")
                .to("direct-vm:execute-sql-check-jwt")
                .log("cek jwt ${body}");
    }
}
