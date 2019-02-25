package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 25 February 2019
 * @Time 21:51
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File SendLoggingToJMSRouteBuilder
 */
public class SendLoggingToJMSRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:send-logging-jms")
                .process(exchange -> {
                    exchange.getIn().setBody(exchange.getProperty("originalBody"));
                    exchange.getIn().setHeader("headerStep", exchange.getIn().getHeader("headerStep"));
                })
                .to(ExchangePattern.InOnly, "jms:queue:jmsTransactionLogging");
    }
}
