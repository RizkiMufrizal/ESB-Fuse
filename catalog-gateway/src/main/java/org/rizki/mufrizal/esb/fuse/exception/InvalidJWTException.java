package org.rizki.mufrizal.esb.fuse.exception;

/**
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 27 February 2019
 * @Time 17:31
 * @Project esb-fuse-service
 * @Package org.rizki.mufrizal.esb.fuse.exception
 * @File InvalidJWTException
 */
public class InvalidJWTException extends RuntimeException {
    public InvalidJWTException(String message) {
        super(message);
    }

    public InvalidJWTException(String message, Throwable throwable) {
        super(message, throwable);
    }
}