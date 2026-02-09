package com.example.SpringSecurity54.services.impl;

import com.example.SpringSecurity54.dto.UserDto;
import com.example.SpringSecurity54.entity.User;
import com.example.SpringSecurity54.exception.ResourceNotFoundException;
import com.example.SpringSecurity54.repository.UserRepository;
import com.example.SpringSecurity54.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException(" email Id "+ email+"Not found"));
    }


}
