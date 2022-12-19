package com.lcxbs.config;

import com.lcxbs.json.annotation.JsonReturnHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public JsonReturnHandler JsonReturnHandler(){
        return new JsonReturnHandler();
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        //注册自定义@JSON注解处理器
        returnValueHandlers.add(JsonReturnHandler());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //配置允许跨域访问
        registry.addMapping("/**").allowCredentials(false).allowedOriginPatterns("*").allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}