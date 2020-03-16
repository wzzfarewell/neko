package com.wzz.neko.exceptions;

/**
 * AuthException
 * 权限认证异常
 * @author wzzfarewell
 * @date 2020/1/10
 **/
public class AuthException extends RuntimeException {
    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
