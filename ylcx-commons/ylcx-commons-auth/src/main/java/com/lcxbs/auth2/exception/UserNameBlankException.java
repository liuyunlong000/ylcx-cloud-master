package com.lcxbs.auth2.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户名为空异常
 */
public class UserNameBlankException extends AuthenticationException {

    public UserNameBlankException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNameBlankException(String msg) {
        super(msg);
    }
}
