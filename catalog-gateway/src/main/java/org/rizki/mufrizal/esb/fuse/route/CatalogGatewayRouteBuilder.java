package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.builder.RouteBuilder;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

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
                .doTry()
                    .process(exchange -> {
                        exchange.getOut().setBody(Response.status(Response.Status.OK).entity("Hello World").build());
                    })
                .doCatch(Exception.class)
                    .to("direct:null-pointer")
                .end();

        from("direct:saveCatalog")
                .to("direct-vm:catalog-repository-save-catalog");

        from("direct:sampleErrorHandler")
                .doTry()
                    .process(exchange -> {
                        Map<String, String> stringMap = new HashMap<>();
                        stringMap.put("Ok", "Oke");
                        exchange.getOut().setBody(stringMap);
                    })
                    .process(exchange -> {
                        exchange.getOut().setBody("Hello");
                    })
                    .log("Trace original message ${body}")
                .doCatch(Exception.class)
                    .to("direct:null-pointer")
                .end();

        from("direct:null-pointer")
                .transform(simple("Message parsing error -- ${exception.message}"))
                .to("log:parse-error?level=ERROR")
                .process(exchange -> {
                    exchange.getOut().setBody(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Server").build());
                });
    }
}
