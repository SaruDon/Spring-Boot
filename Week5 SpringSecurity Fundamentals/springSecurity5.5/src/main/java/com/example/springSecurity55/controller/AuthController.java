package com.example.springSecurity55.controller;

import com.example.springSecurity55.dto.LoginDto;
import com.example.springSecurity55.dto.SignupDto;
import com.example.springSecurity55.dto.UserDto;
import com.example.springSecurity55.services.AuthService;
import com.example.springSecurity55.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        UserDto userDto = userService.signUp(signupDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }
}