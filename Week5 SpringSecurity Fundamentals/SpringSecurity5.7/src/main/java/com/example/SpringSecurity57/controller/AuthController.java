package com.example.SpringSecurity57.controller;

import com.example.SpringSecurity57.dto.LoginDto;
import com.example.SpringSecurity57.dto.LoginResponseDto;
import com.example.SpringSecurity57.dto.SignupDto;
import com.example.SpringSecurity57.dto.UserDto;
import com.example.SpringSecurity57.service.AuthService;
import com.example.SpringSecurity57.service.impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final AuthService authService;



    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signupUser(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(userService.signupUser(signupDto), HttpStatus.CREATED) ;
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

        String refreshToken = Arrays.stream(request.getCookies()).filter(cookie -> "requestToken".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found inside the Coookie"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return loginResponseDto;
    }
}

