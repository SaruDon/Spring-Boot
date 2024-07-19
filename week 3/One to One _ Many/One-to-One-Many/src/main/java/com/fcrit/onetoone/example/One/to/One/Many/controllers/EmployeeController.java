package com.fcrit.onetoone.example.One.to.One.Many.controllers;

import com.fcrit.onetoone.example.One.to.One.Many.entities.DepartmentEntity;
import com.fcrit.onetoone.example.One.to.One.Many.entities.EmployeeEntity;
import com.fcrit.onetoone.example.One.to.One.Many.service.EmployeeService;
import lombok.Builder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    final private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getDepartmentById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.createEmployee(employeeEntity);
    }
}
