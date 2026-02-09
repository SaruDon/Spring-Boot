package com.example.springSecurity55.services;

import com.example.springSecurity55.dto.LoginDto;
import com.example.springSecurity55.dto.UserDto;
import com.example.springSecurity55.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {
        log.info("Login attempt for email: {}", loginDto.getEmail());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            log.info("User authenticated successfully: {}", user.getEmail());
            return jwtService.generateToken(user);

        } catch (Exception e) {
            log.error("Authentication failed for email: {}", loginDto.getEmail(), e);
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

}
