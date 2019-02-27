package org.rizki.mufrizal.esb.fuse.jwt;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 09:25
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.jwt
 * @File JWTToken
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface JWTToken {
}
