package com.lcxbs.auth;

import com.lcxbs.auth.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @Description security 配置
 * ResourceServerConfigurerAdapter 是比WebSecurityConfigurerAdapter 的优先级低的
 */
@Configuration
@EnableWebSecurity
@Order(2)  // WebSecurityConfigurerAdapter 默认为100 这里配置为2设置比资源认证器高
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 匹配oauth相关，匹配健康，匹配默认登录登出 在httpSecurity处理，，其他到ResourceServerConfigurerAdapter OAuth2处理  1
                .requestMatchers().antMatchers("/oauth/**","/druid/**", "/actuator/health", "/login", "/logout")
                .and()
                // 匹配的全部无条件通过 permitAll 2
                .authorizeRequests().antMatchers("/oauth/**","/druid/**", "/actuator/health", "/login", "/logout").permitAll()
                // 匹配条件1的 并且不再条件2通过范围内的其他url全部需要验证登录
                .and().authorizeRequests().anyRequest().authenticated()
                // 启用登录验证
                .and().formLogin().permitAll();
        //druid请求不做CSRF控制
        http.csrf().ignoringAntMatchers("/druid/**").and();
        //关闭X-Content-Type-Options.nosniff,使Druid页面可以正常显示
        http.headers().contentTypeOptions().disable().and();
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    // 验证器加载
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

}
