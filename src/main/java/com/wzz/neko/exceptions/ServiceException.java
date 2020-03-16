package com.wzz.neko.exceptions;

/**
 * 服务（业务）异常，该异常只做INFO级别的日志记录
 * @see com.wzz.neko.config.MyWebMvcConfigurer
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
