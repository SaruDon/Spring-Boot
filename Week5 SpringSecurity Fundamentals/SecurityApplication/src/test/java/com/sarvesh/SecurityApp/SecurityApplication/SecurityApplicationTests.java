package com.sarvesh.SecurityApp.SecurityApplication;

import com.sarvesh.SecurityApp.SecurityApplication.entity.User;
import com.sarvesh.SecurityApp.SecurityApplication.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;


	@Test
	void contextLoads() {

		User user = User.builder()
				.email("sarvesh@gmail.com")
				.password("1234")
				.name("sarvesh")
				.id(4L)
				.build();

		String token = jwtService.generateToken(user);

		System.out.println("token>>"+ token);

		Long id = jwtService.getUserIdFromToken(token);
		System.out.println("Id from token"+id);
	}

}
