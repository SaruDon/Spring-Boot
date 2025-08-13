package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.DriverDto;
import com.sarvesh.project.uber.uber.dto.SignupDto;
import com.sarvesh.project.uber.uber.dto.UserDto;
import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.enums.Role;
import com.sarvesh.project.uber.uber.exceptions.RuntimeConflictException;
import com.sarvesh.project.uber.uber.repositories.UserRepository;
import com.sarvesh.project.uber.uber.security.JwtService;
import com.sarvesh.project.uber.uber.services.AuthService;
import com.sarvesh.project.uber.uber.services.DriverService;
import com.sarvesh.project.uber.uber.services.RiderService;
import com.sarvesh.project.uber.uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;

    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final ModelMapper modelMapper;

    @Override
    public String[] login(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            User user = (User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.refreshToken(user);

            return new String[]{accessToken, refreshToken};

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Authentication failed: " + e.getMessage()) {};
        }
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail())
                .orElse(null);

        if (user!=null){
            throw new  RuntimeConflictException("User already exist with this emailId"+signupDto.getEmail());
        }

        User mappedUser = modelMapper.map(signupDto,User.class);
        mappedUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));
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
    public DriverDto onBoardDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeConflictException("User with id " + userId + " not found"));

        if (user.getRole().contains(Role.DRIVER)) {
            throw new RuntimeException("User is already a driver");
        }

        Driver driver = driverService.onBoardNewDriver(
                Driver.builder()
                        .rating(0.0D)
                        .vehicleId(vehicleId)
                        .available(true)
                        .build()
        );

        user.getRole().add(Role.DRIVER);
        userRepository.save(user);
        return modelMapper.map(driver, DriverDto.class);
    }
}
