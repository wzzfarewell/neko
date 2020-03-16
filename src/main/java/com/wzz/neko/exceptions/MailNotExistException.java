package com.wzz.neko.exceptions;

/**
 * SendMailException
 *
 * @author wzzfarewell
 * @date 2020/2/24
 **/
public class MailNotExistException extends RuntimeException {
    public MailNotExistException() {
        super();
    }

    public MailNotExistException(String message) {
        super(message);
    }

    public MailNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
