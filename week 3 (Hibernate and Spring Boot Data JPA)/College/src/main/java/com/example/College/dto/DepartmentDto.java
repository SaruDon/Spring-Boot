package com.example.College.dto;

import com.example.College.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    private String title;

    private Employee manager;

   private Set<EmployeeDto> employees;

   private Set<EmployeeDto> freelances;
}
