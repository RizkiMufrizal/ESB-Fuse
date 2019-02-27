package org.rizki.mufrizal.esb.fuse.service;

import org.rizki.mufrizal.esb.fuse.helper.JsonObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 13:11
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.service
 * @File JwtGatewayService
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.WILDCARD)
public class JwtGatewayService {

    @Path("/jwt")
    @POST
    public Response generateJwt(JsonObject jsonObject) {
        return null;
    }

}