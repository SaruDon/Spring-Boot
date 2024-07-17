package com.uber.Uber.services;

import com.uber.Uber.dto.DriverDto;
import com.uber.Uber.dto.SignupDto;
import com.uber.Uber.dto.UserDto;

public interface AuthService {

    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardDriver(Long userId);
}
