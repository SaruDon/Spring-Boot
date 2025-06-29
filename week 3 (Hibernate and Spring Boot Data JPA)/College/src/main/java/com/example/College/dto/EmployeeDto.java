package com.example.College.dto;

import com.example.College.entity.Department;
import com.example.College.entity.Projects;
import com.example.College.entity.enums.EmpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;

    private String name;

    private EmpType type;
    // private Department managedDepartment;

    //private Department workerDepartment;

   // private List<Projects> projects;
}
