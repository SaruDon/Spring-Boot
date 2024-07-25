package com.product_ready_features.product_ready_features.clients.impl;

import com.product_ready_features.product_ready_features.advice.ApiResponse;
import com.product_ready_features.product_ready_features.clients.EmployeeClient;
import com.product_ready_features.product_ready_features.dto.EmployeeDTO;
import com.product_ready_features.product_ready_features.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
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
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("trying to retrieve all employees in getAllEmployees");
        try{
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw  new ResourceNotFoundException("count not create employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved all employees in getAllEmployees");
            log.trace("Retrieved employee list in getAllEmployee :{}",employeeDTOList.getData());
            return employeeDTOList.getData();

        }catch (Exception e){
            log.error("Exception occurred in getALlEmployee",e);
            throw new RuntimeException(e);
        }


        /*
        restClient.get():

        Initiates a GET request using the RestClient.
        Used to retrieve data from a specific endpoint using the HTTP GET method.
        .uri("employees"):

        Appends the specified path ("employees") to the base URL of the RestClient.
        Constructs the full URL (e.g., if the base URL is http://localhost:8080/, the full
        URL will be http://localhost:8080/employees).
        .retrieve():

        Tells the RestClient to perform the request and retrieve the response.
        Sends the GET request to the specified URL and waits for the response.
        .body(new ParameterizedTypeReference<>() {}):

        Specifies the type of data expected in the response body.
        Uses ParameterizedTypeReference<List<EmployeeDTO>> to indicate that a list of EmployeeDTO
        objects is expected in the response.

        */

    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Getting employee by ID of {}", employeeId );
        try{

            ApiResponse<EmployeeDTO>  getEmployeeById = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                       log.error(new String(res.getBody().readAllBytes()));
                        throw  new ResourceNotFoundException("count not create employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved employee By Id");

            return getEmployeeById.getData();
        }catch(Exception e){
            log.error("Exception occurred in getEmployee By Id", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.trace("creating employee");
        try{
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw  new ResourceNotFoundException("count not create employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.debug("Created an Employee");
            return employeeDTOApiResponse.getBody().getData();
        }catch (Exception e){
            log.error("Exception occurred while creating employee",e);
            throw  new RuntimeException(e);
        }
    }
}
