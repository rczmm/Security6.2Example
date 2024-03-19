package com.example.demologin.config;

import com.example.demologin.domain.User;
import com.example.demologin.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 捕获请求头中的token字段，判断是否可以获取用户信息
 * 继承OncePerRequestFilter抽象类
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("Authorization");
        System.out.println("Filter token:" + token);
        // login、register请求无token，直接放行
        if (token == null) {
            doFilter(request, response, filterChain);
            return;
        }
        // 存在token，解析token
        Claims claims = null;
        claims = jwtUtil.paresToken(token);
        System.out.println("claims: " + claims);
        String username = claims.get("username", String.class);
        User user = new User();
        user.setUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("authenticationToken: " + authenticationToken);
        doFilter(request, response, filterChain);
    }
}
