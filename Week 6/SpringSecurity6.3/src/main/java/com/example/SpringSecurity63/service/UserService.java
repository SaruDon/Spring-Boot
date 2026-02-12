package com.example.SpringSecurity63.service;


import com.example.SpringSecurity63.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    User findById(Long id);
}
