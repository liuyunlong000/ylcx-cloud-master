package com.lcxbs.auth2.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 密码错误异常
 */
public class PasswordErrorException extends AuthenticationException {

    public PasswordErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PasswordErrorException(String msg) {
        super(msg);
    }
}
