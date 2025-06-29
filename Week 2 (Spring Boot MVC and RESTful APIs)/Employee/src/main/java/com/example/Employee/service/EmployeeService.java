package com.example.Employee.service;

import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.entity.EmployeeEntity;
import com.example.Employee.exceptions.ResourceNotFoundException;
import com.example.Employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final ModelMapper modelMapper;
    private  final EmployeeRepository employeeRepository;


    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }



    public Optional<EmployeeDto> getEmployeeById(Long employeeID) {
        return employeeRepository.findById(employeeID).map(employee -> modelMapper.map(employee,EmployeeDto.class));
    }

    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();

        return employees
                .stream()
                .map(employee->modelMapper.map(employee,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto createNewEmployee(EmployeeDto inputEmployee) {
        EmployeeEntity employeeToBeSaved = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity newEmployee = employeeRepository.save(employeeToBeSaved);
        return modelMapper.map(newEmployee,EmployeeDto.class);
    }

    private Boolean employeeExists(Long id){
        return employeeRepository.existsById(id);
    }

    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto,Long id) {
        if (!employeeExists(id)){
            throw  new ResourceNotFoundException("Employee Not found with id "+id);
        }
        EmployeeEntity employeeToBeUpdated = modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeToBeUpdated.setId(id);
        EmployeeEntity employeeSaved = employeeRepository.save(employeeToBeUpdated);
        return modelMapper.map(employeeSaved, EmployeeDto.class);

    }


    public EmployeeDto partialUpdate(Map<String, Object> updates, Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw  new ResourceNotFoundException("Employee Not found with id "+id);
        }

        EmployeeEntity employee = optionalEmployee.get();

        updates.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            if (fieldToUpdate != null) {
                fieldToUpdate.setAccessible(true);
                ReflectionUtils.setField(fieldToUpdate, employee, value);
            }
        });

        EmployeeEntity updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDto.class);
    }

    public boolean deleteById(Long id) {
        if (!employeeExists(id)){
            throw  new ResourceNotFoundException("Employee Not found with id "+id);
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
