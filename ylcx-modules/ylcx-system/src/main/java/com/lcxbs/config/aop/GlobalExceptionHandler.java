package com.lcxbs.config.aop;

import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.exception.CommonException;
import com.lcxbs.protocol.RespCodeEnum;
import com.lcxbs.protocol.RespMsgBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BindException.class,MissingServletRequestParameterException.class,MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class,Exception.class})
    public RespMsgBean handleBindException(HttpServletRequest request, Exception exception) {
        Map<String, String> map = new HashMap<>();
        //控制台输出异常日志日志
        if (exception instanceof CommonException) {
            CommonException commonException = (CommonException) exception;
            return new RespMsgBean().failure(commonException.getCode(), commonException.getMessage());
        } else if (exception instanceof BindException) {//Model validate 失败
            List<FieldError> allErrors = ((BindException) exception).getFieldErrors();
            StringBuilder sb=new StringBuilder();
            for (FieldError errorMessage : allErrors) {
                if(sb.length()>0){
                    sb.append("<br/><br/>");
                }
                sb.append(errorMessage.getDefaultMessage());
            }
            return new RespMsgBean().failure(RespCodeEnum.FAILURE.getCode(),sb.toString());
        } else if (exception instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException e = (MissingServletRequestParameterException) exception;
            map.put(e.getParameterName(), "缺少参数" + e.getParameterName());
        } else if (exception instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) exception;
            map.put(e.getParameter().getParameterName(), "数据值类型错误");
            return new RespMsgBean().failure(e.getParameter().getParameterName()+"数据值类型错误");
        } else if (exception instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException e = (HttpMessageNotReadableException) exception;
            map.put("", "参数解析错误");
        } else if (exception instanceof AccessDeniedException) {//访问拒绝
            log.debug("访问拒绝",exception);
            return new RespMsgBean().failure(AuthErrorEnum.UNAUTHORIZED.getCode(),AuthErrorEnum.UNAUTHORIZED.getName());
        } else {
            map.put("", exception.getMessage());
            log.error("SYSTEM服务----->全局捕获异常：",exception);
        }
        return new RespMsgBean().failure(map);
    }
}