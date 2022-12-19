package com.lcxbs.config.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 配置OAuth2,启用权限验证
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends GlobalMethodSecurityConfiguration
{
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler()
    {
        return getOAuth2MethodSecurityExpressionHandler();
    }

    /**
     * 概括：HOST方式
     * 携带用户Token信息的restTemplate
     * @作者 FangQiang
     * @创建日期 2019/9/21 10:58
     */
    @Bean(name = "hostRestTemplate")
    public RestTemplate hostRestTemplate() {
        return new RestTemplate();
    }



    @Bean
    public OAuth2MethodSecurityExpressionHandler getOAuth2MethodSecurityExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

}