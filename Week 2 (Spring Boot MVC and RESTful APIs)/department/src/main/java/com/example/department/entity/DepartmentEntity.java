package com.example.department.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // or AUTO, SEQUENCE
    private Long id;
    private String title;
    private Boolean isActive;
    private Double avgSalary;
    private Integer teamSize;
    private String managerName;
    private LocalDateTime createdAt;
}
