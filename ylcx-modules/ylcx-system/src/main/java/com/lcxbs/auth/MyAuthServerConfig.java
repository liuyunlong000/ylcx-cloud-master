package com.lcxbs.auth;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.lcxbs.auth.service.CustomUserDetailsService;
import com.lcxbs.auth2.AuthServerConfig;
import com.lcxbs.auth2.hander.CustomAuthEntryPointHandler;
import com.lcxbs.auth2.hander.CustomClientCredentialsTokenEndpointFilter;
import com.lcxbs.auth2.hander.CustomTokenEnhancer;
import com.lcxbs.auth2.service.MyDefaultTokenServices;
import com.lcxbs.auth2.service.MyJdbcClientDetailsService;
import com.lcxbs.core.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * 认证服务器 （数据库存储的）
 */
@Configuration
@EnableAuthorizationServer
@EnableResourceServer // 程序需要对外暴露获取Token的api和验证Token的API,所以程序也是一个资源服务器
@Order(6)
public class MyAuthServerConfig extends AuthServerConfig {

	@Resource
	protected RedisConnectionFactory redisConnectionFactory;  // redis连接工厂
	@Resource
	protected AuthenticationManager authenticationManager; // 认证方法入口
	@Resource
	protected CustomUserDetailsService customUserDetailsService;  //自定义用户验证数据
	@Resource
	protected WebResponseExceptionTranslator webResponseExceptionTranslator;
	@Resource
	protected CustomAuthEntryPointHandler customAuthEntryPointHandler;
	@Resource
	protected AuthorizationServerEndpointsConfiguration authorizationServerEndpointsConfiguration;

	/**
	 * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务（token services）
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// 配置Token的存储方式
		endpoints.tokenStore(tokenStore());
		// 注入WebSecurityConfig配置的bean
		endpoints.authenticationManager(authenticationManager);
		// 加载token配置
		endpoints.tokenServices(defaultTokenServices());
		// 配置自定义的用户权限数据，不配置会导致token无法刷新
		endpoints.userDetailsService(customUserDetailsService);
		endpoints.userApprovalHandler(userApprovalHandler());
		//配置支持GET和POST方式访问
		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST,HttpMethod.OPTIONS);
		// 该字段设置设置refresh token是否重复使用,true:reuse;false:no reuse.
		endpoints.reuseRefreshTokens(true);
		// 处理 ExceptionTranslationFilter 抛出的异常
		endpoints.exceptionTranslator(webResponseExceptionTranslator);
		//自定义授权集合
		List<TokenGranter> tokenGranters = getTokenGranters(endpoints.getAuthorizationCodeServices(), endpoints.getTokenStore(), endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(),customUserDetailsService);
		endpoints.tokenGranter(new CompositeTokenGranter(tokenGranters));
	}

	/**
	 * 配置令牌端点（Token Endpoint）的安全约束
	 * @param security
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		String path = authorizationServerEndpointsConfiguration.oauth2EndpointHandlerMapping().getServletPath("/oauth/token");
		CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter(security, path);
		endpointFilter.afterPropertiesSet();
		endpointFilter.setAuthenticationEntryPoint(customAuthEntryPointHandler);

		security.authenticationEntryPoint(customAuthEntryPointHandler);
		security.addTokenEndpointAuthenticationFilter(endpointFilter);
		security.tokenKeyAccess("isAuthenticated()");
		security.checkTokenAccess("isAuthenticated()");
	}

	/**
	 * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务
	 * 设置 认证信息的来源：数据库，内存，也可以自己实现ClientDetailsService的loadClientByClientId 方法自定义数据源
	 * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(jdbcClientDetails());//设置校验客户端信息
	}

	@Bean
	public UserApprovalHandler userApprovalHandler() throws Exception {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(jdbcClientDetails()));
		handler.setClientDetailsService(jdbcClientDetails());
		handler.setTokenStore(tokenStore());
		return handler;
	}

	/***
	 * 设置token用redis保存
	 */
	@Bean
	public TokenStore tokenStore() {
		switch (tokenStoreType) {
			case REDIS:
				return new RedisTokenStore(redisConnectionFactory);
			case JWT:
				return new JwtTokenStore(jwtAccessTokenConverter());
		}
		return new InMemoryTokenStore();
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		return super.jwtAccessTokenConverter();
	}

	/**
	 * 加密方式
	 */
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	/**
	 * 配置数据源,注解是指定数据源，否则会有冲突
	 */
	@Bean(name = DynamicDataSource.DEFAULT_DATA_SOURCE_NAME)
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public DataSource defaultDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	/**
	 *  基于 JDBC 实现，需要事先在数据库配置客户端信息
	 */
	@Bean
	public ClientDetailsService jdbcClientDetails() {
		return new MyJdbcClientDetailsService(defaultDataSource());
	}

	/**
	 * 把认证的token保存到redis
	 * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
	 * 自定义的token
	 * 认证的token是存到redis里的 若ClientDetails中设定了有效时间则以设定值为准
	 */
	@Primary
	@Bean
	public DefaultTokenServices defaultTokenServices() {
		MyDefaultTokenServices tokenServices = new MyDefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setClientDetailsService(jdbcClientDetails());
		tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);  // token有效期自定义设置，默认12小时
		tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);  // refresh_token默认30天
		tokenServices.setReuseRefreshToken(true);
		tokenServices.setTokenEnhancer(tokenEnhancer());
		return tokenServices;
	}

	@Bean
	public CustomTokenEnhancer tokenEnhancer(){
		return new CustomTokenEnhancer();
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		StringRedisSerializer serializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(serializer);
		redisTemplate.setValueSerializer(serializer);
		return redisTemplate;
	}
}
