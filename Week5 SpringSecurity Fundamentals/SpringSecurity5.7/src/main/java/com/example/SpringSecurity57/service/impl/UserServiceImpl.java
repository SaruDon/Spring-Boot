package com.example.SpringSecurity57.service.impl;

import com.example.SpringSecurity57.dto.LoginDto;
import com.example.SpringSecurity57.dto.SignupDto;
import com.example.SpringSecurity57.dto.UserDto;
import com.example.SpringSecurity57.entity.User;
import com.example.SpringSecurity57.exception.ResourceNotFoundException;
import com.example.SpringSecurity57.repostory.UserRepository;
import com.example.SpringSecurity57.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.models.annotations.internal.DiscriminatorOptionsAnnotation;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("Not Found"));
    }


    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found"));
    }

    @Override
    public UserDto signupUser(SignupDto signupDto){
        //TODO
        /*
            1)Find user if it exist.
            2)If user already exist then return
            3)
         */
        Optional<User> user  = userRepository.findByEmail(signupDto.getEmail());
        log.info("user{}",user.toString());
        if(user.isPresent()){
            throw  new RuntimeException("User already exists");
        }

        User userToSave = modelMapper.map(signupDto,User.class);
        userToSave.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        return modelMapper.map(userRepository.save(userToSave),UserDto.class);
    }



}
