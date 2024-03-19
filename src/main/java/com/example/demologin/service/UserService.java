package com.example.demologin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demologin.domain.User;

public interface UserService extends IService<User> {
    int register(User user);

    String login(User user);

}
