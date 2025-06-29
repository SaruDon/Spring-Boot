package com.example.college.services;

import com.example.college.entity.DepartmentEntity;
import com.example.college.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {


    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public DepartmentEntity createNewDepartment(DepartmentEntity department){
        return departmentRepository.save(department);
    }

    public DepartmentEntity getDepartmentById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }
}
