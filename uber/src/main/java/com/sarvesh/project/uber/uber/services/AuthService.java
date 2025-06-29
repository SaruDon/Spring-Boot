package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.dto.DriverDto;
import com.sarvesh.project.uber.uber.dto.SignupDto;
import com.sarvesh.project.uber.uber.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String login(String email,String password);
    UserDto signup(SignupDto signupDto);
    DriverDto onBoardDriver(Long userId);
}
