package com.sarvesh.project.uber.uber.controllers;

import com.sarvesh.project.uber.uber.dto.*;
import com.sarvesh.project.uber.uber.services.AuthService;
import com.sarvesh.project.uber.uber.services.impl.DriverServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {


    private final AuthService authService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        UserDto userDto = authService.signup(signupDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnBoardDriverDto onBoardDriverDto, Authentication authentication){
        logger.debug("User:>>> " + authentication.getName());
        logger.debug("Authorities:>> " + authentication.getAuthorities());


        return new ResponseEntity<>(authService.onBoardDriver(userId,onBoardDriverDto.getVehicleId()),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        String tokens[]= authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());

        Cookie cookie = new Cookie("token",tokens[1]);
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }

}
