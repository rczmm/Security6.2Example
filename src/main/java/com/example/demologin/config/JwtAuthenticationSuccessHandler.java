package com.example.demologin.config;

import com.example.demologin.domain.User;
import com.example.demologin.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功的拦截器，在此demo没用这里的作用是在响应和控制台打印出token，方便测试
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;


    public JwtAuthenticationSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        String token = jwtUtil.createToken(map);
        System.out.println("Handler token:" + token);
        response.getWriter().write(token);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
