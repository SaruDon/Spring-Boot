package com.fcrit.onetoone.example.One.to.One.Many.repository;

import com.fcrit.onetoone.example.One.to.One.Many.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
