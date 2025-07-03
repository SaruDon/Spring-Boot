package com.example.production_feature.clients.impl;


import com.example.production_feature.advices.ApiResponse;
import com.example.production_feature.clients.EmployeeClient;
import com.example.production_feature.dto.EmployeeDto;
import com.example.production_feature.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;


    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDto> getAllEmployees() {
//        log.error("Error log");
//        log.warn("Warn log");
//        log.info("Info Log");
//        log.debug("Debug log");
//        log.trace("trace log");
        log.trace("Trying to retrieve all employees in getAllEmployees");
        try{
            ResponseEntity<ApiResponse<List<EmployeeDto>>> employeeDtoList = restClient.get()
                    .uri("/employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully got the employees in getAllEmployees");
            log.trace("Retrieved employee list in getAllEmployees :{}",employeeDtoList.getBody().getData());
            return  employeeDtoList.getBody().getData();
        }catch (Exception e){
            log.error("Exception occurred in getAllEmployees",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        log.trace("Getting Employees By id");
        try{
            ResponseEntity<ApiResponse<EmployeeDto>> employee = restClient
                    .get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw  new ResourceNotFoundException("Employee with Id,"+employeeId+"Not found");
                    })
                    .toEntity(new ParameterizedTypeReference<>(){});

            log.debug("Successfully retrieved the employee");
            return employee.getBody().getData();
        }catch (Exception e){
            log.error("Exception occurred in getAllEmployees");
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        log.trace("Creating new employee");
        try {
            ResponseEntity<ApiResponse<EmployeeDto>> employeeDtoApiResponse = restClient
                    .post()
                    .uri("employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.debug("is4xxClientError");
                        log.error("Error occured"+new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<ApiResponse<EmployeeDto>>() {
                    });
            log.trace("Successfully created a new Employee:{}", employeeDtoApiResponse.getBody().getData());
            return employeeDtoApiResponse.getBody().getData();
        }catch (Exception e){
            log.error("Exception occurred while creating employee", e);
            throw new RuntimeException(e);
        }
    }
}
