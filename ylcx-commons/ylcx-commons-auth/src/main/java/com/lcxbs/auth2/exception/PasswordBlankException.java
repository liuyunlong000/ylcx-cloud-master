package com.lcxbs.auth2.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 密码为空异常
 */
public class PasswordBlankException extends AuthenticationException {

    public PasswordBlankException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PasswordBlankException(String msg) {
        super(msg);
    }
}
