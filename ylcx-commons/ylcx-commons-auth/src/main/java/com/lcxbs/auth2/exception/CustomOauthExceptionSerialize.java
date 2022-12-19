package com.lcxbs.auth2.exception;

import cn.hutool.core.date.DateUtil;
import com.lcxbs.auth2.enums.AuthErrorEnum;
import com.lcxbs.protocol.RespCodeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * 自定义认证错误序列化实现
 */
public class CustomOauthExceptionSerialize extends StdSerializer<CustomOauthException> {

    public static final String BAD_MSG = "Bad credentials";

    public CustomOauthExceptionSerialize(){
        super(CustomOauthException.class);
    }

    protected CustomOauthExceptionSerialize(Class<CustomOauthException> t) {
        super(t);
    }

    @Override
    public void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        jsonGenerator.writeStartObject();
        Throwable cause=e.getCause();
        int code= RespCodeEnum.SUCCESS.getCode();
        String msg=RespCodeEnum.SUCCESS.getName();
        String message=cause.getMessage();
        System.out.println(message);
        if (message.equalsIgnoreCase("Token was not recognised")) {
            code=AuthErrorEnum.TOKEN_INVALID.getCode();
            msg=AuthErrorEnum.TOKEN_INVALID.getName();
        }else{
            code= RespCodeEnum.FAILURE.getCode();
            msg=RespCodeEnum.FAILURE.getName();
        }
        jsonGenerator.writeStringField("data","");
        jsonGenerator.writeStringField("code", String.valueOf(code));
        jsonGenerator.writeStringField("msg",msg);
        jsonGenerator.writeStringField("nowTime", DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        jsonGenerator.writeEndObject();
    }
}