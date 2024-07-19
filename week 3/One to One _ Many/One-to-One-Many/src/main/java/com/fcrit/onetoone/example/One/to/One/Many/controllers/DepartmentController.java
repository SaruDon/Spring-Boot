package com.fcrit.onetoone.example.One.to.One.Many.controllers;

import com.fcrit.onetoone.example.One.to.One.Many.entities.DepartmentEntity;
import com.fcrit.onetoone.example.One.to.One.Many.service.DepartmentService;
import lombok.Builder;
import org.springframework.web.bind.annotation.*;

@Builder
@RestController
@RequestMapping("/department")
public class DepartmentController {

    final private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId){
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.createDepartment(departmentEntity);
    }


    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId){
        return departmentService.assignManagerToDepartment(departmentId,employeeId);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId){
        return  departmentService.getAssignedDepartmentOfManager(employeeId);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId){
        return departmentService.assignWorkerToDepartment(departmentId,employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignFreeLancerToDepartment(@PathVariable Long departmentId,
                                                     @PathVariable Long employeeId){
        return departmentService.assignfreelanceDepartments(departmentId,employeeId);
    }
}
