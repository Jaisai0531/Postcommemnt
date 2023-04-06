package com.myblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MyblogApplicationTests {
	public static PasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

	public static void main(String[] args) {
		String test = bCryptPasswordEncoder.encode("test");
		System.out.println(test);

	}

}
