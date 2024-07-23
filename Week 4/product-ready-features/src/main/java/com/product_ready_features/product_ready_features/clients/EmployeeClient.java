package com.product_ready_features.product_ready_features.clients;


import com.product_ready_features.product_ready_features.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
}
