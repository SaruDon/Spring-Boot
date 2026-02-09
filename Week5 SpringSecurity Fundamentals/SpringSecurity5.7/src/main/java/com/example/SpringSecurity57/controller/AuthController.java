package com.example.SpringSecurity57.controller;

import com.example.SpringSecurity57.dto.LoginDto;
import com.example.SpringSecurity57.dto.SignupDto;
import com.example.SpringSecurity57.dto.UserDto;
import com.example.SpringSecurity57.service.AuthService;
import com.example.SpringSecurity57.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }


}

