package com.fcrit.onetoone.example.One.to.One.Many.repository;

import com.fcrit.onetoone.example.One.to.One.Many.entities.DepartmentEntity;
import com.fcrit.onetoone.example.One.to.One.Many.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
