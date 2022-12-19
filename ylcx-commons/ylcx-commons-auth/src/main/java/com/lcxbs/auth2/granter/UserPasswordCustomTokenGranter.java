package com.lcxbs.auth2.granter;

import com.lcxbs.auth2.model.CustomUser;
import com.lcxbs.auth2.service.MyUserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * 密码授权类型
 */
public class UserPasswordCustomTokenGranter extends AbstractCustomTokenGranter {

    protected MyUserDetailsService userDetailsService;

    public UserPasswordCustomTokenGranter(MyUserDetailsService userDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "password");
        this.userDetailsService = userDetailsService;
    }



    @Override
    protected CustomUser getCustomUser(Map<String, String> parameters){
        String username = parameters.get("username");
        String password = parameters.get("password");
        return userDetailsService.loadUserByUsernameAndPassword(username,password);
    }

}