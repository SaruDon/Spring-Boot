package com.sarvesh.project.uber.uber.controllers;

import com.sarvesh.project.uber.uber.dto.SignupDto;
import com.sarvesh.project.uber.uber.dto.UserDto;
import com.sarvesh.project.uber.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("/signup")
    private ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        return ResponseEntity.ok(authService.signup(signupDto));
    }

}
