package com.wzz.neko.exceptions;

/**
 * SendMailException
 *
 * @author wzzfarewell
 * @date 2020/2/24
 **/
public class SendMailException extends RuntimeException {
    public SendMailException() {
        super();
    }

    public SendMailException(String message) {
        super(message);
    }

    public SendMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
