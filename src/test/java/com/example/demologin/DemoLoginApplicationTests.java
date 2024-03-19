package com.example.demologin;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DemoLoginApplicationTests {

	@Resource
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		String password = passwordEncoder.encode("123456");
		System.out.println(password);


	}

}
