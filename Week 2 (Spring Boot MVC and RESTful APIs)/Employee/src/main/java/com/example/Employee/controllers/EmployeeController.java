package com.example.Employee.controllers;

import com.example.Employee.config.MapperConfig;
import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.exceptions.ResourceNotFoundException;
import com.example.Employee.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeID){
        Optional<EmployeeDto> employeeDto = employeeService.getEmployeeById(employeeID);
        return employeeDto
                .map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not found with id "+employeeID));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false) Integer age){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto inputEmployee){
        return new ResponseEntity<>(employeeService.createNewEmployee(inputEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto ,  @PathVariable Long id){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDto,id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> partialUpdate(@RequestBody Map<String, Object>updates , @PathVariable Long id){
        return ResponseEntity.ok(employeeService.partialUpdate(updates,id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        boolean gotDeleted = employeeService.deleteById(id);
        if (gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

}
