package com.example.demologin.controller;

import com.example.demologin.domain.User;
import com.example.demologin.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(User user) {
        String token = userService.login(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        System.out.println(headers);
        return ResponseEntity.ok().headers(headers).body("Login successful! Token:" + token);
    }
}
