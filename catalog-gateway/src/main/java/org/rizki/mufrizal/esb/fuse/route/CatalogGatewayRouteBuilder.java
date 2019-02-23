package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.builder.RouteBuilder;

import javax.ws.rs.core.Response;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 23 February 2019
 * @Time 18:20
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File CatalogGatewayRouteBuilder
 */
public class CatalogGatewayRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:getCatalogs")
                .process(exchange -> {
                    exchange.getOut().setBody(Response.status(Response.Status.OK).entity("Hello World").build());
                }).end();
    }
}
