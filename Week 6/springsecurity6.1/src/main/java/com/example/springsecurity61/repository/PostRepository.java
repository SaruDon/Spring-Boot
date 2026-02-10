package com.example.springsecurity61.repository;

import com.example.springsecurity61.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PostRepository extends JpaRepository<Long, Post> {
}
