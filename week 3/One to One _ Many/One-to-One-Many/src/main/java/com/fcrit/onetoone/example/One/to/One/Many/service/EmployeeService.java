package com.fcrit.onetoone.example.One.to.One.Many.service;

import com.fcrit.onetoone.example.One.to.One.Many.entities.EmployeeEntity;
import com.fcrit.onetoone.example.One.to.One.Many.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    final private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public EmployeeEntity createEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }
}
