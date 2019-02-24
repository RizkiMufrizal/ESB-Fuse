package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import java.util.HashMap;
import java.util.UUID;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 24 February 2019
 * @Time 15:27
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File LoggingRouteBuilder
 */
public class LoggingRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:queue:jmsTransactionLogging")
                .process(exchange -> {
                    HashMap objectHashMap = exchange.getIn().getBody(HashMap.class);
                    objectHashMap.put("body", objectHashMap.toString());
                    objectHashMap.put("id", UUID.randomUUID().toString());
                    objectHashMap.put("headerStep", exchange.getIn().getHeader("headerStep"));
                    exchange.getIn().setBody(objectHashMap);
                })
                .log(LoggingLevel.INFO, "logging MQ ${body} ${header.headerStep}")
                .to("sql:INSERT INTO tb_step_log(id, txn_ref_no, payload, step) VALUES (:#id, :#TxnRefNo, :#body, :#headerStep)");
    }
}