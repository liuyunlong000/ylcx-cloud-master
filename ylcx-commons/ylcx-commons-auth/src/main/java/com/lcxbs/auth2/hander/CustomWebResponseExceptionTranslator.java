package com.lcxbs.auth2.hander;

import com.lcxbs.auth2.exception.CustomOauthException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * Web响应异常转换器
 */
@Component
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    public static final String BAD_MSG = "Bad credentials";

    /**
     * Web响应异常处理
     * @param e spring security内部异常
     * @return 经过处理的异常信息
     * @throws Exception 通用异常
     */
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
        //自定义授权异常处理
        CustomOauthException exception = new CustomOauthException(e.getMessage(),e);
        int code = exception.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(responseEntity.getHeaders().toSingleValueMap());
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        headers.set("Access-Control-Max-Age", "3600");
        headers.set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        ResponseEntity<OAuth2Exception> response = new ResponseEntity<>(exception, null, HttpStatus.valueOf(code));
        return response;
    }

}