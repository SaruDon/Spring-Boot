package com.example.College.controller;

import com.example.College.dto.EmployeeDto;
import com.example.College.entity.Employee;
import com.example.College.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private  final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/hello")
    public String hello(){
        System.out.println("Hello");
        return "hello";
    }


    @GetMapping()
    private  ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    private ResponseEntity<EmployeeDto> getEmployee (@PathVariable Long id){
        Optional<EmployeeDto> employeeDto = employeeService.getEmployee(id);
        return employeeDto
                .map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElse(null);
    }

    @PostMapping
    private ResponseEntity<EmployeeDto> createEmployee (@RequestBody EmployeeDto inputEmployee){
      EmployeeDto savedEmployee = employeeService.createEmployee(inputEmployee);
      return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<EmployeeDto> updateEmployee (@PathVariable Long id,@RequestBody EmployeeDto employee){
        EmployeeDto updatedEmployee = employeeService.updateById(id,employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<EmployeeDto> partialUpdates(@PathVariable Long id, @RequestBody Map<String, Objects> updates){
        EmployeeDto updatedEmployee = employeeService.partialUpdate(id,updates);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id){
        boolean gotDeleted =employeeService.deleteById(id);
        if (!gotDeleted){
             return ResponseEntity.notFound().build();
        }
       return  ResponseEntity.ok(gotDeleted);
    }

    @PutMapping("{empId}/assignWorkerToDepartment/{departmentId}")
    private ResponseEntity<EmployeeDto> assignWorkerToDepartment(@PathVariable Long empId, @PathVariable Long departmentId){
        EmployeeDto employee = employeeService.assignWorkerADepartment(empId,departmentId);
        return ResponseEntity.ok(employee);
    }


    @PutMapping("{empId}/assignFreelancerToDepartment/{departmentId}")
    private ResponseEntity<EmployeeDto> assignFreelancerToDepartment(@PathVariable Long empId, @PathVariable Long departmentId){
        EmployeeDto employee = employeeService.assignFreelancerADepartment(empId,departmentId);
        return ResponseEntity.ok(employee);
    }

//    @PutMapping("{empId}/assignProjectToEmployee/{projectId}")
//    private ResponseEntity<EmployeeDto> assignProjectToEmployee(@PathVariable Long empId, @PathVariable Long projectId){
//        EmployeeDto employee = employeeService.assignProjectToEmployee(empId,projectId);
//        return ResponseEntity.ok(employee);
//    }
}
