package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.rs.security.jose.jwa.SignatureAlgorithm;
import org.apache.cxf.rs.security.jose.jws.HmacJwsSignatureProvider;
import org.apache.cxf.rs.security.jose.jws.JwsJwtCompactProducer;
import org.apache.cxf.rs.security.jose.jwt.JwtClaims;

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
                .process(exchange -> {

                    long iat = Instant.now().getEpochSecond();
                    long exp = Instant.now().plusSeconds(10L).getEpochSecond();

                    JwtClaims jwtClaims = new JwtClaims();
                    jwtClaims.setSubject("rizki");
                    jwtClaims.setExpiryTime(exp);
                    jwtClaims.setIssuedAt(iat);

                    JwsJwtCompactProducer jwsProducer = new JwsJwtCompactProducer(jwtClaims);
                    String token = jwsProducer.signWith(new HmacJwsSignatureProvider(Base64.getEncoder().encodeToString("secret".getBytes()), SignatureAlgorithm.HS256));

                    Map<String, Object> jwtResponse = new HashMap<>();
                    jwtResponse.put("Token", token);
                    jwtResponse.put("Username", "rizki");
                    jwtResponse.put("Iat", iat);
                    jwtResponse.put("Exp", exp);

                    exchange.getOut().setBody(Response.status(Response.Status.OK).entity(jwtResponse).build());
                }).end();
    }
}
