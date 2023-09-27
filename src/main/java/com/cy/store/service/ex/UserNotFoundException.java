package com.cy.store.service.ex;

import lombok.Data;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-07-12  22:03
 * @Description: TODO
 */

public class UserNotFoundException extends ServiceException{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
