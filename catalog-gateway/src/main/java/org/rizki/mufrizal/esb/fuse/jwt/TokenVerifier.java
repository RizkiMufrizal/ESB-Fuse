package org.rizki.mufrizal.esb.fuse.jwt;

import org.apache.cxf.rs.security.jose.jwa.SignatureAlgorithm;
import org.apache.cxf.rs.security.jose.jws.HmacJwsSignatureVerifier;
import org.apache.cxf.rs.security.jose.jws.JwsException;
import org.apache.cxf.rs.security.jose.jws.JwsJwtCompactConsumer;
import org.apache.cxf.rs.security.jose.jwt.JwtClaims;
import org.rizki.mufrizal.esb.fuse.exception.InvalidJWTException;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 17:25
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.jwt
 * @File TokenVerifier
 */
public class TokenVerifier {

    private AuthorizationSendSql authorizationSendSql;

    private String token;

    public TokenVerifier(AuthorizationSendSql authorizationSendSql, String token) {
        this.authorizationSendSql = authorizationSendSql;
        this.token = token;
    }

    public void tokenValid() {
        try {
            JwsJwtCompactConsumer jwsConsumer = new JwsJwtCompactConsumer(this.token);

            Map<String, String> stringMap = new HashMap<>();
            stringMap.put("Username", jwsConsumer.getJwtClaims().getSubject());

            LinkedCaseInsensitiveMap<String> linkedCaseInsensitiveMap = this.authorizationSendSql.sendSql(stringMap);
            String secret = linkedCaseInsensitiveMap.get("secret");

            boolean isTokenValid = jwsConsumer.verifySignatureWith(new HmacJwsSignatureVerifier(Base64.getEncoder().encodeToString(secret.getBytes()), SignatureAlgorithm.HS256));

            if (!isTokenValid) {
                throw new InvalidJWTException(String.format("JWT Token Not Valid %s", this.token));
            }

            new ClaimVerifier(jwsConsumer.getJwtClaims()).validateExpireTime();

        } catch (JwsException e) {
            e.printStackTrace();
            throw new InvalidJWTException(String.format("JWT Token Not Valid %s", this.token));
        }
    }

    private class ClaimVerifier {
        private JwtClaims jwtClaims;

        public ClaimVerifier(JwtClaims jwtClaims) {
            this.jwtClaims = jwtClaims;
        }

        ClaimVerifier validateExpireTime() {
            long now = Instant.now().getEpochSecond();
            if (now > this.jwtClaims.getExpiryTime()) {
                throw new InvalidJWTException(String.format("JWT expired since %s (reference clock is %s).", Instant.ofEpochSecond(jwtClaims.getExpiryTime()), Instant.ofEpochSecond(now)));
            }
            return this;
        }
    }
}
