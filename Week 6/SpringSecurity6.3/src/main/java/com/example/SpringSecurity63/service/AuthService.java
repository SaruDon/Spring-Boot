package com.example.SpringSecurity63.service;

import com.example.SpringSecurity63.dto.LoginDto;
import com.example.SpringSecurity63.dto.LoginResponseDto;
import com.example.SpringSecurity63.dto.SignUpDto;
import com.example.SpringSecurity63.dto.UserDto;

public interface AuthService {
    UserDto signUp(SignUpDto signUpDto);

    LoginResponseDto login(LoginDto loginDto);

    LoginResponseDto refresh(String refresh);
}
