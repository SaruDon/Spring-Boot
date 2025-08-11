package com.sarvesh.SecurityApp.SecurityApplication.service;

import com.sarvesh.SecurityApp.SecurityApplication.dto.LoginDto;
import com.sarvesh.SecurityApp.SecurityApplication.dto.SignUpDto;
import com.sarvesh.SecurityApp.SecurityApplication.dto.UserDto;
import com.sarvesh.SecurityApp.SecurityApplication.entity.User;
import com.sarvesh.SecurityApp.SecurityApplication.exception.ResourceNotFoundException;
import com.sarvesh.SecurityApp.SecurityApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("User with email "+username+" not found"));
    }

    public UserDto signUp(SignUpDto signUpDto) {
        try {
            Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());
            if (user.isPresent()){
                throw  new RuntimeException("User with email already exists"+signUpDto.getEmail());
            }
            User tobeCreateduser = modelMapper.map(signUpDto,User.class);
            tobeCreateduser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            User savedUser = userRepository.save(tobeCreateduser);
            return modelMapper.map(savedUser,UserDto.class);
        }catch (Exception e){
            throw new RuntimeException("Exception"+e);
        }
    }

    public User getUserByUserId(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user with if"+userId+"not found"));
    }
}
