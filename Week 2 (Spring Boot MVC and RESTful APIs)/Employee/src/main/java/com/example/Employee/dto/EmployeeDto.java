package com.example.Employee.dto;

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
    Long id;
    String name;
    LocalDate doj;
    Float salary;
    String role;
    Boolean isActive;
}
