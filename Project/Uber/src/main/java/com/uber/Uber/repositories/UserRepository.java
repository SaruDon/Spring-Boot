package com.uber.Uber.repositories;

import com.uber.Uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
