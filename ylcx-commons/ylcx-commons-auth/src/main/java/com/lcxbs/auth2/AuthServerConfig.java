package com.lcxbs.auth2;

import com.lcxbs.auth2.granter.CustomRefreshTokenGranter;
import com.lcxbs.auth2.granter.UserPasswordCustomTokenGranter;
import com.lcxbs.auth2.model.CustomUser;
import com.lcxbs.auth2.service.MyUserDetailsService;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 *
 * 认证服务器配置
 */
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	public TokenStoreType tokenStoreType = TokenStoreType.REDIS;

	public enum TokenStoreType {
		IN_MEMORY, REDIS, JWT
	}

	/**
	 * 自定义授权类型
	 * @param authorizationCodeServices
	 * @param tokenStore
	 * @param tokenServices
	 * @param clientDetailsService
	 * @param requestFactory
	 * @return
	 */
	protected List<TokenGranter> getTokenGranters(AuthorizationCodeServices authorizationCodeServices, TokenStore tokenStore, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, MyUserDetailsService userDetailsService) {
		return new ArrayList<>(Arrays.asList(
				new RefreshTokenGranter(tokenServices, clientDetailsService, requestFactory),
				new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory),
				new UserPasswordCustomTokenGranter(userDetailsService, tokenServices, clientDetailsService, requestFactory)
		));
	}

	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("auth_server");
		((DefaultAccessTokenConverter) accessTokenConverter.getAccessTokenConverter()).setUserTokenConverter(new DefaultUserAuthenticationConverter() {
			@Override
			public Authentication extractAuthentication(Map<String, ?> map) {
				CustomUser customUser = new CustomUser();
				BeanMap.create(customUser).putAll(map);
				Object authorities = map.get("authorities");
				if (authorities instanceof String) {
					customUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities));
				} else if (authorities instanceof Collection) {
					customUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection) authorities)));
				}
				return new PreAuthenticatedAuthenticationToken(customUser, null, customUser.getAuthorities());
			}
		});
		return accessTokenConverter;
	}

}


