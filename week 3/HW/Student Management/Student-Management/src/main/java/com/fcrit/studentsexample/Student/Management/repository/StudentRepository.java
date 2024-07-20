package com.fcrit.studentsexample.Student.Management.repository;

import com.fcrit.studentsexample.Student.Management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
