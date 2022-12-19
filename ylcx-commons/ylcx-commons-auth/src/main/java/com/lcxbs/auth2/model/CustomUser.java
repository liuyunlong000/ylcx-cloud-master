package com.lcxbs.auth2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * 自定义用户对象
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomUser extends User implements AuthenticatedPrincipal, Serializable {

    private String id;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUser(){
        super("","",null);
    }

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Override
    public String getName() {
        return this.getUsername();
    }

    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}