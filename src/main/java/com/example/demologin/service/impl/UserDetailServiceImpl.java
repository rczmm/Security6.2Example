package com.example.demologin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demologin.domain.User;
import com.example.demologin.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    /*
     * 根据用户名查询
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        log.info("loadUserByUsername:" + user);
        return user;
    }
}
