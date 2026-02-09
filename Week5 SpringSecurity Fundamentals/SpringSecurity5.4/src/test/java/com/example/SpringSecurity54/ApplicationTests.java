package com.example.SpringSecurity54;

import com.example.SpringSecurity54.entity.User;
import com.example.SpringSecurity54.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SignatureException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void testJwtGenerationAndExtraction() {
		// Arrange: Create a test user
		User user = User.builder()
				.email("sarvesh@gmail.com")
				.password("1234")
				.name("sarvesh")
				.id(4L)
				.build();

		// Act: Generate token
		String token = jwtService.generateToken(user);
		System.out.println("Generated Token: " + token);

		// Assert: Token should not be null or empty
		assertNotNull(token);
		assertFalse(token.isEmpty());

		// Act: Extract user ID from token
		Long extractedId = jwtService.getUserIdFromToken(token);
		System.out.println("Extracted User ID: " + extractedId);

		// Assert: Extracted ID should match original
		assertEquals(user.getId(), extractedId);
	}

	@Test
	void testTokenExpiration() throws InterruptedException {
		User user = User.builder()
				.id(1L)
				.email("test@example.com")
				.build();

		String token = jwtService.generateToken(user);

		// Wait for token to expire (61 seconds)
		Thread.sleep(61000);

		// This should throw ExpiredJwtException
		assertThrows(ExpiredJwtException.class, () -> {
			jwtService.getUserIdFromToken(token);
		});
	}

	@Test
	void testInvalidTokenSignature() {
		// Create a token
		User user = User.builder().id(1L).email("test@example.com").build();
		String token = jwtService.generateToken(user);

		// Tamper with the token (change one character)
		String tamperedToken = token.substring(0, token.length() - 5) + "XXXXX";

		// This should throw SignatureException
		assertThrows(SignatureException.class, () -> {
			jwtService.getUserIdFromToken(tamperedToken);
		});
	}
}



