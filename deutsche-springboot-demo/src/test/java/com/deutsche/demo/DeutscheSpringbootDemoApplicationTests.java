package com.deutsche.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class DeutscheSpringbootDemoApplicationTest {

	@Autowired
	private PasswordEncoder passwordEncoder; // Example bean to test

	@Test
	void contextLoads() {
		assertNotNull(passwordEncoder, "PasswordEncoder bean should be loaded");
	}
}