package com.example.SpringSecurity57.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Post {

    @Id
    private Long id;

    private String title;

    private String description;

    private String content;
}
