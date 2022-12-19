package com.lcxbs.auth2.hander;

import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.json.JsonUtil;
import com.lcxbs.protocol.RespCodeEnum;
import com.lcxbs.protocol.RespMsgBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截resource返回的异常信息
 * 
 */
@Component(value = "customAuthEntryPointHandler")
public class CustomAuthEntryPointHandler implements AuthenticationEntryPoint, AccessDeniedHandler, AuthenticationFailureHandler {

    private static final Logger log= LoggerFactory.getLogger(CustomAuthEntryPointHandler.class);

    /**
     * AuthenticationEntryPoint
     * 
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Throwable cause = authException.getCause();
        this.createHttpHeader(response, HttpServletResponse.SC_OK);
        int code= RespCodeEnum.FAILURE.getCode();
        String msg=RespCodeEnum.FAILURE.getName();
        if(authException instanceof BadCredentialsException){//客户端凭证错误
            code= AuthErrorEnum.INVALID_CLIENT.getCode();
            msg=AuthErrorEnum.INVALID_CLIENT.getName();
        }else {
            // 资源未授权
        }
        log.error("",authException);
        RespMsgBean result=new RespMsgBean();
        result.setCode(code);
        result.setMsg(msg);
        response.getWriter().write(JsonUtil.toJson(result));
    }

    /**
     * AccessDeniedHandler
     * 
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {
        log.error(">>>>>AccessDeniedException error:", accessDeniedException);
        // 访问资源的用户权限不足
        this.createHttpHeader(response, HttpServletResponse.SC_FORBIDDEN);
        RespMsgBean result = new RespMsgBean().failure(AuthErrorEnum.INSUFFICIENT_PERMISSIONS);
        response.getWriter().write(JsonUtil.toJson(result));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException {
        log.error(">>>>>onAuthenticationFailure error:", exception);
        this.createHttpHeader(response, HttpServletResponse.SC_FORBIDDEN);
        // 资源未授权
        RespMsgBean result = new RespMsgBean().failure(AuthErrorEnum.UNAUTHORIZED);
        response.getWriter().write(JsonUtil.toJson(result));
    }

    private void createHttpHeader(HttpServletResponse response, int status) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        // CORS "pre-flight" request
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control", "no.cache");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
    }

}