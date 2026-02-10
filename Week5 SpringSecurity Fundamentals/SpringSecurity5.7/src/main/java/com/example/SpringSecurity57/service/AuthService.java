package com.example.SpringSecurity57.service;

import com.example.SpringSecurity57.dto.LoginDto;
import com.example.SpringSecurity57.dto.LoginResponseDto;
import com.example.SpringSecurity57.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto login(LoginDto loginDto){

        try{
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginDto.getEmail(),
                                    loginDto.getPassword()
                            )
                    );

            User user = (User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);



            return new LoginResponseDto(user.getId(),accessToken,refreshToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public LoginResponseDto refresh(String)
}
