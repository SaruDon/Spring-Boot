package com.fcrit.week2HomeWorkexample.Department.controller;

import com.fcrit.week2HomeWorkexample.Department.dto.DepartmentDto;
import com.fcrit.week2HomeWorkexample.Department.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    final private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long departmentId){
        Optional<DepartmentDto> departmentDto = departmentService.getDepartmentById(departmentId);
        return departmentDto.map(departmentDto1 -> ResponseEntity.ok(departmentDto1)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getListOfDepartment(){
        return ResponseEntity.ok(departmentService.getListOfDepartment());
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long departmentId){
        boolean isFound = departmentService.deleteDepartment(departmentId);
        if (isFound) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartmentByID(@RequestBody DepartmentDto departmentDto, @PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentByID(departmentDto,departmentId));
    }

    @PatchMapping(path = "{departmentId}")
    public ResponseEntity<DepartmentDto> updatePartialDepartmentById(@PathVariable Long departmentId,@RequestBody @Valid Map<String,Object> updates){
        DepartmentDto departmentDto = departmentService.updatePartialDepartmentById(departmentId,updates);
        if (departmentDto==null) ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDto);
    }


}
