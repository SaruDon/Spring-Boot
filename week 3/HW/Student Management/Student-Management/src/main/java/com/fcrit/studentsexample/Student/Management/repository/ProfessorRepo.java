package com.fcrit.studentsexample.Student.Management.repository;

import com.fcrit.studentsexample.Student.Management.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepo extends JpaRepository<Professor,Long>{
}
