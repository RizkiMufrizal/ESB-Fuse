package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.rizki.mufrizal.esb.fuse.helper.JsonObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                .process(exchange -> exchange.getIn().setHeader("headerStep", "catalog-repository-save-catalog"))
                .to(ExchangePattern.InOnly, "jms:queue:jmsTransactionLogging")
                .to("sql:INSERT INTO tb_catalog(catalog_id, catalog_name, catalog_price) VALUES (:#CatalogId, :#CatalogName, :#CatalogPrice)")
                .process(exchange -> {
                    JsonObject jsonObject = exchange.getIn().getBody(JsonObject.class);

                    Map<String, Object> objectMap = new HashMap<>();
                    objectMap.put("ResponseTime", new Date());
                    objectMap.put("ResponseMessage", "Success");
                    objectMap.put("ResponseCode", "MW-000");

                    Map<String, Object> objectMapData = new HashMap<>();
                    objectMapData.put("CatalogId", jsonObject.getString("CatalogId"));
                    objectMapData.put("CatalogName", jsonObject.getString("CatalogName"));
                    objectMapData.put("CatalogPrice", jsonObject.getString("CatalogPrice"));

                    Map<String, Object> objectMapResponse = new HashMap<>();
                    objectMapResponse.put("Data", objectMapData);
                    objectMapResponse.put("Response", objectMap);

                    exchange.getIn().setBody(objectMapResponse);
                })
                .end();
    }
}