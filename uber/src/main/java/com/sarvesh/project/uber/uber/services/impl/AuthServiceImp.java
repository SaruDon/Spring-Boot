package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.DriverDto;
import com.sarvesh.project.uber.uber.dto.SignupDto;
import com.sarvesh.project.uber.uber.dto.UserDto;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.enums.Role;
import com.sarvesh.project.uber.uber.exceptions.RuntimeConflictException;
import com.sarvesh.project.uber.uber.repositories.UserRepository;
import com.sarvesh.project.uber.uber.services.AuthService;
import com.sarvesh.project.uber.uber.services.RiderService;
import com.sarvesh.project.uber.uber.services.WalletService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;

    private final RiderService riderService;
    private final WalletService walletService;

    private final ModelMapper modelMapper;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail())
                .orElse(null);

        if (user!=null){
            throw new  RuntimeConflictException("User already exist with this emailId"+signupDto.getEmail());
        }

        User mappedUser = modelMapper.map(signupDto,User.class);
        mappedUser.setRole(Set.of(Role.RIDER));
        User savedUser =userRepository.save(mappedUser);

        //TODO
        //create user related entities
        riderService.createNewRider(mappedUser);

        //Create Wallet
        walletService.createNewWallet(mappedUser);

        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onBoardDriver(Long userId) {
        return null;
    }
}
