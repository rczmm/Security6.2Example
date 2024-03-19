package com.example.demologin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    String username;

    String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * 获取用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    /**
     * 判断用户是否过期，未过期返回true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * 判断账号是否被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 判断用户凭据是否过期需要更新
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断用户是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
