package com.example.department.controller;

import com.example.department.dto.DepartmentDto;
import com.example.department.exceptions.ResourceNotFound;
import com.example.department.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long departmentId){
        Optional<DepartmentDto> departmentDto = departmentService.getDepartmentById(departmentId);
        return departmentDto
                .map(dept-> ResponseEntity.ok(dept))
                .orElseThrow(()-> new ResourceNotFound("resource not found with if"+departmentId));
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllEmployee(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }


    @PostMapping()
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto newDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable Long id){
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto,id);
        return ResponseEntity.ok(updatedDepartment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDto> partialUpdate(@RequestBody Map<String,Object>updates,@PathVariable Long id){
        DepartmentDto departmentDto = departmentService.partialUpdate(updates,id);
        return  ResponseEntity.ok(departmentDto);
    }

}
