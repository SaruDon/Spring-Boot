package com.example.SpringSecurity62.controller;

import com.example.SpringSecurity62.dto.LoginDto;
import com.example.SpringSecurity62.dto.LoginResponseDto;
import com.example.SpringSecurity62.dto.SignUpDto;
import com.example.SpringSecurity62.dto.UserDto;
import com.example.SpringSecurity62.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {


    private final AuthService authService;

    @PostMapping(path = "/signup")
    public UserDto signUp(@RequestBody SignUpDto signUpDto){
        return authService.signUp(signUpDto);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping(path = "refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){

        String refreshToken =
                Arrays.stream(request.getCookies()).filter(cookie -> "refreshToken".equals(cookie.getName()))
                        .findFirst()
                        .map(cookie -> cookie.getValue())
                        .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found inside the Coookie"));

        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}
