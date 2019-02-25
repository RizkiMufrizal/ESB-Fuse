package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 25 February 2019
 * @Time 21:50
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File LoggingToFileRouteBuilder
 */
public class LoggingToFileRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:logging-body")
                .log(LoggingLevel.INFO, "logging body ${body}");
    }
}
