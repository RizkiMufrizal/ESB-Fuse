package org.rizki.mufrizal.esb.fuse.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 23 February 2019
 * @Time 18:21
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.service
 * @File CatalogGatewayService
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogGatewayService {

    @Path("/catalogs")
    @GET
    public Response getCatalogs() {
        return null;
    }

}