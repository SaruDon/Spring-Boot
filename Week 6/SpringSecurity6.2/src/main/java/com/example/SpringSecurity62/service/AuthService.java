package com.example.SpringSecurity62.service;

import com.example.SpringSecurity62.dto.LoginDto;
import com.example.SpringSecurity62.dto.LoginResponseDto;
import com.example.SpringSecurity62.dto.SignUpDto;
import com.example.SpringSecurity62.dto.UserDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    UserDto signUp(SignUpDto signUpDto);

    LoginResponseDto login(LoginDto loginDto);

    LoginResponseDto refreshToken(String refreshToken);


}
