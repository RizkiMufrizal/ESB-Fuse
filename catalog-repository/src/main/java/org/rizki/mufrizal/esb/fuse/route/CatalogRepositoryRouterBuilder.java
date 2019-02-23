package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 23 February 2019
 * @Time 19:10
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File CatalogRepositoryRouterBuilder
 */
public class CatalogRepositoryRouterBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct-vm:catalog-repository-save-catalog")
                .log(LoggingLevel.INFO, "logging ${body}")
                .to("sql:INSERT INTO tb_catalog(catalog_id, catalog_name, catalog_price) VALUES (:#catalogId, :#catalogName, :#catalogPrice)")
                .end();
    }
}