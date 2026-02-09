package com.example.springSecurity55.services;

import com.example.springSecurity55.dto.SignupDto;
import com.example.springSecurity55.dto.UserDto;
import com.example.springSecurity55.entity.User;
import com.example.springSecurity55.exception.ResourceNotFoundException;
import com.example.springSecurity55.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(" email Id "+ email+"Not found"));
    }


    public UserDto signUp(SignupDto signupDto){
        Optional<User> user = userRepository.findByEmail(signupDto.getEmail());

        if(user.isPresent()){
            throw new RuntimeException("user already exist with user email"+signupDto.getEmail());
        }

        User userToBeSaved = modelMapper.map(signupDto,User.class);
        userToBeSaved.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        return modelMapper.map(userRepository.save(userToBeSaved),UserDto.class);
    }
}
