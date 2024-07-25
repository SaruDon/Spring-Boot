package com.uber.Uber.controllers;


import com.uber.Uber.dto.SignupDto;
import com.uber.Uber.dto.UserDto;
import com.uber.Uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(path = "/signup")
    private UserDto signUp(@RequestBody SignupDto signupDto){
        return authService.signup(signupDto);
    }
}
