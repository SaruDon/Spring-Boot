package com.sarvesh.SecurityApp.SecurityApplication.service;

import com.sarvesh.SecurityApp.SecurityApplication.dto.LoginDto;
import com.sarvesh.SecurityApp.SecurityApplication.entity.User;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(LoginDto loginDto) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            User user = (User) authentication.getPrincipal();
            return jwtService.generateToken(user);
        }catch (Exception e) {
            // For any other unexpected exceptions, wrap in AuthenticationException
            throw new BadCredentialsException("Authentication failed", e);
        }
    }

}
