package com.uber.Uber.services.impl;

import com.uber.Uber.dto.DriverDto;
import com.uber.Uber.dto.SignupDto;
import com.uber.Uber.dto.UserDto;
import com.uber.Uber.entities.Rider;
import com.uber.Uber.entities.User;
import com.uber.Uber.entities.enums.Role;
import com.uber.Uber.exception.RunTimeConflictException;
import com.uber.Uber.repositories.UserRepository;
import com.uber.Uber.services.AuthService;
import com.uber.Uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private  final ModelMapper modelMapper;
    private  final UserRepository userRepository;
    private  final RiderService riderService;


    @Override
    public String login(String email, String password) {


        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        //while signup need to create things that user might user
        // for.eg wallet

        User userCheck = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (userCheck!=null){
            throw new  RunTimeConflictException("User Already exist with email"+ signupDto.getEmail());
        }



        User user = modelMapper.map(signupDto, User.class);
        user.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user);

        //create user related entities
        riderService.createRider(user);
        //TODO add Wallet service

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public DriverDto onboardDriver(Long userId) {
        return null;
    }
}
