package com.example.SpringSecurity63.service.impl;

import com.example.SpringSecurity63.dto.LoginDto;
import com.example.SpringSecurity63.dto.LoginResponseDto;
import com.example.SpringSecurity63.dto.SignUpDto;
import com.example.SpringSecurity63.dto.UserDto;
import com.example.SpringSecurity63.entity.User;
import com.example.SpringSecurity63.repository.UserRepository;
import com.example.SpringSecurity63.service.AuthService;
import com.example.SpringSecurity63.service.JwtService;
import com.example.SpringSecurity63.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        try{

            Optional<User> user = userService.findByEmail(signUpDto.getEmail());

            if(user.isPresent()){
                throw  new RuntimeException("User Already exists");
            }

            User createNewUser = modelMapper.map(signUpDto,User.class);
            createNewUser.setPassword(passwordEncoder.encode(createNewUser.getPassword()));

            return modelMapper.map(userRepository.save(createNewUser), UserDto.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        User user =(User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return LoginResponseDto
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginResponseDto refresh(String refreshToken) {
        try{
            Long userId = jwtService.getUserIdFromToken(refreshToken);
            User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Not found"));
            String accessToken = jwtService.generateAccessToken(user);

            LoginResponseDto loginResponseDto = new LoginResponseDto(user.getId(), accessToken,refreshToken);
            return loginResponseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
