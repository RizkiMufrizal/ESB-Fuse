package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 23 February 2019
 * @Time 19:10
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File CatalogRepositoryRouteBuilder
 */
public class CatalogRepositoryRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct-vm:catalog-repository-save-catalog")
                .setProperty("originalBody", body())
                .to("sql:SELECT id, service_code, step_service, compensation, is_ignore_exception, seq_no FROM tb_step where service_code = :#ServiceCode order by seq_no")
                .log("logging sql ${body}")
                .split(simple("${body}"))
                .process(exchange -> exchange.getIn().setHeader("headerStep", "catalog-repository-save-catalog"))
                .recipientList(simple("${body.get('step_service')}"));
    }
}