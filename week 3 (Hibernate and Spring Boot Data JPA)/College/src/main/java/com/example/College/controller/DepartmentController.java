package com.example.College.controller;


import com.example.College.dto.DepartmentDto;
import com.example.College.dto.EmployeeDto;
import com.example.College.entity.Department;
import com.example.College.service.DepartmentService;
import com.mysql.cj.log.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping
    private ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    private ResponseEntity<DepartmentDto> getEmployeeById(@PathVariable Long id){
        Optional<DepartmentDto> departmentDtoOptional = departmentService.getDepartmentId(id);
        return departmentDtoOptional
                .map(departmentDto -> ResponseEntity.ok(departmentDto))
                .orElse(null);
    }

    @PostMapping
    private ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto department){
        DepartmentDto department1 = departmentService.createDepartment(department);
        return  new ResponseEntity<DepartmentDto>(department1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto department){
        DepartmentDto departmentDto = departmentService.updateDepartment(id,department);
        return ResponseEntity.ok(departmentDto);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<DepartmentDto> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Objects> updates){
        DepartmentDto departmentDto = departmentService.partialUpdates(id,updates);
        return ResponseEntity.ok(departmentDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id) {
        Boolean gotDeleted = departmentService.deleteById(id);
        if (!gotDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gotDeleted);
    }

    @PutMapping("{departmentId}/manager/{id}")
    private ResponseEntity<DepartmentDto> assignManagerToDepartment(@PathVariable Long departmentId,@PathVariable Long id){
        DepartmentDto departmentDto = departmentService.assignManagerToDepartment(departmentId,id);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping("/assignedDepartmentToManager/{id}")
    private ResponseEntity<DepartmentDto> getAssignedManagerOfDepartment(@PathVariable Long id){
        DepartmentDto departmentDto = departmentService.getAssignedManagerOfDepartment(id);
        return ResponseEntity.ok(departmentDto);
    }
}
