package com.example.production_feature.clients;


import com.example.production_feature.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long employeeId);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
}
