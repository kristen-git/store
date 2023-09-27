package com.cy.store.service.ex;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-02-20  20:32
 * @Description: TODO
 */
/** 业务异常的基类 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
