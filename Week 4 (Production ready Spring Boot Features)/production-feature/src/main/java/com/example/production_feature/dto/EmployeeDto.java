package com.example.production_feature.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeDto {
    private Long id;
    private String name;
    private LocalDate dateOfJoining;
    private Float salary;
    private String role;
    private Boolean isActive;
}