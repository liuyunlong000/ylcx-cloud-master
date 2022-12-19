package com.lcxbs.auth2.service;

import com.lcxbs.auth2.model.CustomUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义用户认证与授权
 */
public class MyUserDetailsService implements UserDetailsService {


    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /**
     * 用户名和密码校验
     * @param username
     * @param password
     * @return
     * @throws UsernameNotFoundException
     */
    public CustomUser loadUserByUsernameAndPassword(String username,String password){
        return null;
    }
}
