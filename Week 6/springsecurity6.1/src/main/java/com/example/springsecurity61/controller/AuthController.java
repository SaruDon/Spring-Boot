package com.example.springsecurity61.controller;

import com.example.springsecurity61.dto.LoginDto;
import com.example.springsecurity61.dto.LoginResponseDto;
import com.example.springsecurity61.dto.SignupDto;
import com.example.springsecurity61.dto.UserDto;
import com.example.springsecurity61.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public UserDto signup(@RequestBody SignupDto signupDto){
        return authService.signup(signupDto);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse httpServletResponse){

        LoginResponseDto loginResponseDto =authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken",loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){

        String refreshToken = Arrays.stream(request
                .getCookies()).filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found inside the Coookie"));



        return ResponseEntity.ok(authService.refresh(refreshToken));
    }
}
