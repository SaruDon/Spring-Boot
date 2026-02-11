package com.example.SpringSecurity62.service.impl;

import com.example.SpringSecurity62.entity.User;
import com.example.SpringSecurity62.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity62.repository.UserRepository;
import com.example.SpringSecurity62.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Not found"));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found id"+id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
