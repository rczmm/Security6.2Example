package com.example.demologin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demologin.domain.User;
import com.example.demologin.service.UserService;
import com.example.demologin.mapper.UserMapper;

import com.example.demologin.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    UserMapper userMapper;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    JwtUtil jwtUtil;

    @Override
    public int register(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userMapper.insert(user);
        return 0;
    }

    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            log.info("用户名或密码错误！");
            return "用户名或密码错误！";
        }

        User user1 = (User) authentication.getPrincipal();
        if (user1 == null) {
            return "用户名或密码错误";
        }

        Map<String, Object> map = new HashMap<>();

        map.put("username", user1.getUsername());
        String token = jwtUtil.createToken(map);
        System.out.println(token);
        return token;
    }
}
