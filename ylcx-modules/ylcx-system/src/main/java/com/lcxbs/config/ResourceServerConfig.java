package com.lcxbs.config;

import com.lcxbs.config.exception.CustomAccessDeniedHandler;
import com.lcxbs.config.exception.CustomAuthExceptionEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ResourceServer配置
 */
@Component
@RefreshScope
@EnableResourceServer
@EnableWebSecurity
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(ResourceServerConfig.class);

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${security.oauth2.authorization.check-token-access}")
    private String checkTokenEndpointUrl;

    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Resource
    private CustomAuthExceptionEntryPoint customAuthExceptionEntryPoint;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        tokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);

        resources.tokenServices(tokenService);
        resources.authenticationEntryPoint(customAuthExceptionEntryPoint); //用来响应匿名用户访问无权限资源时的异常
        resources.accessDeniedHandler(customAccessDeniedHandler);//用来响应认证过的用户访问无权限资源时的异常
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 排除不需要授权访问的路径
        http
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/*").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/sysCommonDict/find_map").permitAll()
                .antMatchers("/cxTrip/pass").permitAll()
                .antMatchers("/wzBoardOverview/*").permitAll()
                .antMatchers("/wzComplanyOrg/*").permitAll()
                .antMatchers("/wzHome/find_list").permitAll()
                .antMatchers("/wzInnovate/find_id").permitAll()
                .antMatchers("/wzInnovate/find_list").permitAll()
                .antMatchers("/wzInnovateEcology/find_id").permitAll()
                .antMatchers("/wzInnovateEcology/find_list").permitAll()
                .antMatchers("/wzInnovatePlateForm/find_id").permitAll()
                .antMatchers("/wzInnovatePlateForm/find_list").permitAll()
                .antMatchers("/wzLeader/find_id").permitAll()
                .antMatchers("/wzLeader/find_list").permitAll()
                .antMatchers("/wzNews/find_id").permitAll()
                .antMatchers("/wzNews/find_list").permitAll()
                .antMatchers("/wzNews/find_list_home").permitAll()
                .antMatchers("/wzNotice/find_id").permitAll()
                .antMatchers("/wzNotice/find_list_home").permitAll()
                .antMatchers("/wzPersonnelDynamic/find_id").permitAll()
                .antMatchers("/wzPersonnelDynamic/find_list_home").permitAll()
                .antMatchers("/wzPersonnelRecruit/find_id").permitAll()
                .antMatchers("/wzPersonnelRecruit/find_list").permitAll()
                .antMatchers("/wzPersonnelRecruit/find_list_home").permitAll()
                .antMatchers("/wzPersonnelOverview/find_id").permitAll()
                .antMatchers("/wzPersonnelOverview/find_list").permitAll()
                .antMatchers("/wzProduct/find_id").permitAll()
                .antMatchers("/wzProduct/find_list").permitAll()
                .antMatchers("/wzScientific/find_id").permitAll()
                .antMatchers("/wzScientific/find_list").permitAll()
                .antMatchers("/wzScientificDynamic/find_id").permitAll()
                .antMatchers("/wzScientificDynamic/find_list").permitAll()
                .antMatchers("/wzScientificDynamic/find_list_home").permitAll()
                .antMatchers("/ueditor/*").permitAll()
                .antMatchers("/ueditor/image/*").permitAll()
                .antMatchers("/ueditor/video/*").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .headers().frameOptions().disable();
        log.info("Security Access Control is enabled on Resource server Application");
    }
}