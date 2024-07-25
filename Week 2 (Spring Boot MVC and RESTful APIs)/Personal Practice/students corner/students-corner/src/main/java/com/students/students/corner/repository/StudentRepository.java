package com.students.students.corner.repository;

import com.students.students.corner.entity.StudentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentsEntity, Long> {

}
