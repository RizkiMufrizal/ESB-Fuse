package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 25 February 2019
 * @Time 21:53
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File ExecuteSQLCatalogRouteBuilder
 */
public class ExecuteSQLCatalogRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:execute-sql-catalog")
                .process(exchange -> {
                    exchange.getIn().setBody(exchange.getProperty("originalBody"));
                    exchange.getIn().setHeader("headerStep", exchange.getIn().getHeader("headerStep"));
                })
                .to("sql:INSERT INTO tb_catalog(catalog_id, catalog_name, catalog_price) VALUES (:#CatalogId, :#CatalogName, :#CatalogPrice)");
    }
}
