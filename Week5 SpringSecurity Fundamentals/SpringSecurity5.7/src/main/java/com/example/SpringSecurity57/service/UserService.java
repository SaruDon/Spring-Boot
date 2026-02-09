package com.example.SpringSecurity57.service;

import com.example.SpringSecurity57.dto.LoginDto;
import com.example.SpringSecurity57.dto.SignupDto;
import com.example.SpringSecurity57.dto.UserDto;

public interface UserService {
    UserDto signupUser(SignupDto signupDto);

}
