package org.rizki.mufrizal.esb.fuse.route;

import org.apache.camel.builder.RouteBuilder;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 20:48
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.route
 * @File JwtRouteBuilder
 */
public class JwtRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct-vm:execute-sql-jwt")
                .to("sql:SELECT secret, username, exp FROM tb_user where username = :#Username and password = :#Password?outputType=SelectOne");

        from("direct-vm:execute-sql-check-jwt")
                .to("sql:SELECT secret, username FROM tb_user where username = :#Username?outputType=SelectOne");
    }
}