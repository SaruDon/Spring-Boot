package com.sarvesh.SecurityApp.SecurityApplication.controller;

import com.sarvesh.SecurityApp.SecurityApplication.dto.LoginDto;
import com.sarvesh.SecurityApp.SecurityApplication.dto.LoginResponseDto;
import com.sarvesh.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.sarvesh.SecurityApp.SecurityApplication.dto.UserDto;
import com.sarvesh.SecurityApp.SecurityApplication.service.AuthService;
import com.sarvesh.SecurityApp.SecurityApplication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto){
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return  ResponseEntity.ok(loginResponseDto);
    }


    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest httpServletRequest){
        String refreshToken = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie1 -> "refreshToken".equals(cookie1.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("RefreshToken not found inside the Cookies"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
        return  ResponseEntity.ok(loginResponseDto);
    }
}
