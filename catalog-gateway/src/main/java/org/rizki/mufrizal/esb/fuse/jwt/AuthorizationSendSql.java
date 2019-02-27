package org.rizki.mufrizal.esb.fuse.jwt;

import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.Map;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 22:31
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.jwt
 * @File AuthorizationSendSql
 */
public interface AuthorizationSendSql {
    LinkedCaseInsensitiveMap<String> sendSql(Map<String, String> sql);
}