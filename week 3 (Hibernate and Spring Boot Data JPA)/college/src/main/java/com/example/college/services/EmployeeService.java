package com.example.college.services;


import com.example.college.entity.EmployeeEntity;
import com.example.college.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {


    private  final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }
}
