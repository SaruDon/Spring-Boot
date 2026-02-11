package com.example.SpringSecurity62.service.impl;

import com.example.SpringSecurity62.dto.LoginDto;
import com.example.SpringSecurity62.dto.LoginResponseDto;
import com.example.SpringSecurity62.dto.SignUpDto;
import com.example.SpringSecurity62.dto.UserDto;
import com.example.SpringSecurity62.entity.User;
import com.example.SpringSecurity62.repository.UserRepository;
import com.example.SpringSecurity62.service.AuthService;
import com.example.SpringSecurity62.service.JwtService;
import com.example.SpringSecurity62.service.UserService;
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

    private final UserServiceImpl userServiceImpl;
    private final JwtService jwtService;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        /*
            TODO
            1.check if user already exists?
                yes: runtime exception
                no: create new user

           2) create new user
           3) return userDto

         */
        try{
            Optional<User> user  = userServiceImpl.findByEmail(signUpDto.getEmail());
            if(user.isPresent()){
                throw new RuntimeException("User already exist");
            }

            User userCreated = modelMapper.map(signUpDto,User.class);
            userCreated.setPassword(passwordEncoder.encode(userCreated.getPassword()));

            return modelMapper.map(userRepository.save(userCreated), UserDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LoginResponseDto login(LoginDto loginDto){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);;
            String refreshToken = jwtService.generateRefreshTokenToken(user);

            LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken,refreshToken);

            return loginResponseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        /*Todo
         * 1) get password
         * check password
         *
         * */
        try{

            Long userId = jwtService.getUserIdFromToken(refreshToken);
            User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Not found"));
            String accessToken = jwtService.generateAccessToken(user);

            LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken,refreshToken);
            return loginResponseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
