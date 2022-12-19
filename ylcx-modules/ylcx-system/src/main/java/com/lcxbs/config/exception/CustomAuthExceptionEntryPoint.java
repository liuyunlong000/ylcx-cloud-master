package com.lcxbs.config.exception;

import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  用来响应匿名用户访问无权限资源时的异常
 */
@Component(value = "customAuthExceptionEntryPoint")
public class CustomAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final Logger log= LoggerFactory.getLogger(CustomAuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            RespMsgBean respMsgBean=new RespMsgBean(AuthErrorEnum.TOKEN_INVALID.getCode(),AuthErrorEnum.TOKEN_INVALID.getName(),authException.getMessage());
            log.info("",authException);
            response.getWriter().write(JsonUtil.objectToJson(respMsgBean));
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

}