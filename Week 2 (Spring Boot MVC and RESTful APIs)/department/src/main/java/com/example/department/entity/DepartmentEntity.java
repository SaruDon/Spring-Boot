package com.example.department.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class DepartmentEntity {
    @Id
    private Long id;
    private String title;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
