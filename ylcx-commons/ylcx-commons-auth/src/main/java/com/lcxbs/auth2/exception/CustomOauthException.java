package com.lcxbs.auth2.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义鉴权认证
 */
@JsonSerialize(using = CustomOauthExceptionSerialize.class)
public class CustomOauthException extends OAuth2Exception {

    public CustomOauthException(String msg, Throwable t) {
        super(msg, t);
    }

}