package com.example.SpringSecurity62.service;

import com.example.SpringSecurity62.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    User findById(Long id);

    User save(User user);
}
