package com.sarvesh.SecurityApp.SecurityApplication.service;

import com.sarvesh.SecurityApp.SecurityApplication.dto.LoginDto;
import com.sarvesh.SecurityApp.SecurityApplication.dto.LoginResponseDto;
import com.sarvesh.SecurityApp.SecurityApplication.entity.User;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public LoginResponseDto login(LoginDto loginDto) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(),  loginDto.getPassword())
            );
            User user = (User) authentication.getPrincipal();
            String accessToken  = jwtService.generateAccessToken(user);
            String refreshToken  = jwtService.generateRefreshToken(user);
            return new LoginResponseDto();
        }catch (Exception e) {
            // For any other unexpected exceptions, wrap in AuthenticationException
            throw new BadCredentialsException("Authentication failed", e);
        }
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserByUserId(userId);

        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(),accessToken,refreshToken);
    }
}
