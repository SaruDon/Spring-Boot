package com.fcrit.week2HomeWorkexample.Department.repository;

import com.fcrit.week2HomeWorkexample.Department.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
