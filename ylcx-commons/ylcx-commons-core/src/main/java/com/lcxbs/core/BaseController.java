package com.lcxbs.core;


import com.lcxbs.protocol.RespMsgBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 〈控制器的基类〉
 */
public class BaseController {

    private static final Logger logger= LoggerFactory.getLogger(BaseController.class);

    protected static final String SUCCESS = "操作成功";

    protected static final String UPDATE_SUCCESS = "更新成功";

    protected static final String SAVE_SUCCESS = "保存成功";

    protected static final String FIND_SUCCESS = "查询成功";

    protected static final String DELETE_SUCCESS = "删除成功";

    protected static final String FAILURE = "操作失败";

    protected static final String UPDATE_FAILURE = "更新失败";

    protected static final String SAVE_FAILURE = "保存失败";

    protected static final String FIND_FAILURE = "查询失败";

    protected static final String DELETE_FAILURE = "删除失败";

    protected static final String VERSION_2 = "V2";

    protected static final String VERSION_3 = "V3";

    public RespMsgBean success() {
        return new RespMsgBean().success();
    }

    public RespMsgBean success(Integer code) {
        return new RespMsgBean().success(code);
    }

    public RespMsgBean success(String msg) {
        return new RespMsgBean().success(msg);
    }

    public RespMsgBean success(Object data) {
        return new RespMsgBean().success(data);
    }

    public RespMsgBean success(Integer code, String msg) {
        return new RespMsgBean().success(code, msg);
    }

    public RespMsgBean success(String msg, Object data) {
        return new RespMsgBean().success(msg, data);
    }

    public RespMsgBean success(Integer code, String msg, Object data) {
        return new RespMsgBean().success(code, msg, data);
    }

    public RespMsgBean failure() {
        return new RespMsgBean().failure();
    }

    public RespMsgBean failure(Integer code) {
        return new RespMsgBean().failure(code);
    }

    public RespMsgBean failure(String msg) {
        return new RespMsgBean().failure(msg);
    }

    public RespMsgBean failure(Object data) {
        return new RespMsgBean().failure(data);
    }

    public RespMsgBean failure(Integer code, String msg) {
        return new RespMsgBean().failure(code, msg);
    }

    public RespMsgBean failure(String msg, Object data) {
        return new RespMsgBean().failure(msg, data);
    }

    public RespMsgBean failure(Integer code, String msg, Object data) {
        return new RespMsgBean().failure(code, msg, data);
    }

    /**
     * 获取token（首先到请求header中key为Authorization获取，如果没找到，则找请求参数access_token）
     * @param request
     * @return
     */
    protected String extractToken(HttpServletRequest request) {
        String token = this.extractHeaderToken(request);
        if (token == null) {
            logger.debug("Token not found in headers. Trying request parameters.");
            token = request.getParameter("access_token");
            if (token == null) {
                logger.debug("Token not found in request parameters.  Not an OAuth2 request.");
            } else {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, "Bearer");
            }
        }
        return token;
    }

    /**
     * 从请求headers中获取token
     * @param request
     * @return
     */
    protected String extractHeaderToken(HttpServletRequest request) {
        Enumeration headers = request.getHeaders("Authorization");
        String value;
        do {
            if (!headers.hasMoreElements()) {
                return null;
            }
            value = (String) headers.nextElement();
        } while (!value.toLowerCase().startsWith("Bearer".toLowerCase()));
        String authHeaderValue = value.substring("Bearer".length()).trim();
        request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, value.substring(0, "Bearer".length()).trim());
        int commaIndex = authHeaderValue.indexOf(44);
        if (commaIndex > 0) {
            authHeaderValue = authHeaderValue.substring(0, commaIndex);
        }
        return authHeaderValue;
    }
}