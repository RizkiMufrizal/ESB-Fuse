package org.rizki.mufrizal.esb.fuse.jwt;

import org.apache.cxf.rs.security.jose.jws.JwsException;
import org.rizki.mufrizal.esb.fuse.exception.InvalidJWTException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 09:27
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.jwt
 * @File JWTTokenFilter
 */
@Provider
@JWTToken
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String headerJWT = containerRequestContext.getHeaderString("ESB-JWT");

        if (headerJWT != null) {
            String token = headerJWT.substring("Token".length()).trim();

            try {
                new TokenVerifier(token, "secret").tokenValid();
            } catch (JwsException | InvalidJWTException e) {
                e.printStackTrace();
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }

        } else {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }
}