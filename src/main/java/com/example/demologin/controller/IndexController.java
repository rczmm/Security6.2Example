package com.example.demologin.controller;

import com.example.demologin.domain.User;
import com.example.demologin.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IndexController {

    @Resource
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.register(user);
        return "index";
    }
}
