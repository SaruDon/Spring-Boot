package com.example.SpringSecurity63.repository;

import com.example.SpringSecurity63.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
