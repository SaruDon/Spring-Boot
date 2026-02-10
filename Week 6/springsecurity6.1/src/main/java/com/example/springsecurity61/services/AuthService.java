package com.example.springsecurity61.services;

import com.example.springsecurity61.dto.LoginDto;
import com.example.springsecurity61.dto.LoginResponseDto;
import com.example.springsecurity61.dto.SignupDto;
import com.example.springsecurity61.dto.UserDto;
import com.example.springsecurity61.entity.User;
import com.example.springsecurity61.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserDto signup(SignupDto signupDto){
        //ToDo
        /*
        1)Check if user already exist?
            Yes: Erorr
            No :Create new user
        2) Model mapper to map these details and create new user
         */

        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());

        if (user.isPresent()){
            throw new RuntimeException("user already exist with emailId"+ signupDto.getEmail());
        }


        User userCreated = modelMapper.map(signupDto,User.class);
        userCreated.setPassword(passwordEncoder.encode(userCreated.getPassword()));

        userRepository.save(userCreated);

        return modelMapper.map(userCreated,UserDto.class);
    }

    public LoginResponseDto login(LoginDto loginDto) {
        /*Todo
        * 1) get password
        * check password
        *
        * */
        try{
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginDto.getEmail(),
                                    loginDto.getPassword()
                            )
                    );

            User user =(User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            LoginResponseDto loginResponseDto = new LoginResponseDto(user.getId(), accessToken,refreshToken);
            return loginResponseDto;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public LoginResponseDto refresh(String refreshToken) {
        /*Todo
         * 1) get password
         * check password
         *
         * */
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
