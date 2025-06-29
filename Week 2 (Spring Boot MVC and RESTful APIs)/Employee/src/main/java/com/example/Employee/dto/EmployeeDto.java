package com.example.Employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;
    private LocalDate dateOfJoining;
    private Float salary;
    private String role;
    @JsonProperty("isActive")
    private Boolean isActive;
}
