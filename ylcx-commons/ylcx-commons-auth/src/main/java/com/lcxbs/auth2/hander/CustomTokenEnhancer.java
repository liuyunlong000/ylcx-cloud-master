package com.lcxbs.auth2.hander;

import cn.hutool.core.lang.UUID;
import com.lcxbs.auth2.model.CustomUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义TokenEnhancer
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);
            CustomUser user = (CustomUser) authentication.getPrincipal();
            token.setValue(getNewToken());
            OAuth2RefreshToken refreshToken = token.getRefreshToken();
            if (refreshToken instanceof DefaultOAuth2RefreshToken) {
                token.setRefreshToken(new DefaultOAuth2RefreshToken(getNewToken()));
            }
            Map<String, Object> additionalInformation = new HashMap<String, Object>();
            additionalInformation.put("user_id", user.getId());
            additionalInformation.put("user_name", user.getUsername());
            additionalInformation.put("full_name", user.getFullName());
            additionalInformation.put("login_time", System.currentTimeMillis());
            token.setAdditionalInformation(additionalInformation);
            return token;
        }
        return accessToken;
    }
    private String getNewToken() {
        String tokenValue = UUID.randomUUID().toString().replace("-","") + UUID.randomUUID().toString().replace("-","");
        return tokenValue;
    }
}