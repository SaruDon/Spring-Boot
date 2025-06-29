package com.example.College.dto;

import com.example.College.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long project_id;

    private String title;

    private List<Employee> members;

}
