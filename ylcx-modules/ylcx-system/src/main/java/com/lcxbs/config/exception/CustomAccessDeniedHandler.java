package com.lcxbs.config.exception;

import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.protocol.RespMsgBean;
import com.lcxbs.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来响应认证过的用户访问无权限资源时的异常
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log= LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            RespMsgBean respMsgBean=new RespMsgBean(AuthErrorEnum.UNAUTHORIZED.getCode(),AuthErrorEnum.UNAUTHORIZED.getName());
            response.getWriter().write(JsonUtil.objectToJson(respMsgBean));
        } catch (IOException e) {
            log.error("",e);
        }
    }

}