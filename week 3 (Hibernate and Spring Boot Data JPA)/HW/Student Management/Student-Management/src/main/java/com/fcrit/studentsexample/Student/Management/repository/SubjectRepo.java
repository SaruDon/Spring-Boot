package com.fcrit.studentsexample.Student.Management.repository;

import com.fcrit.studentsexample.Student.Management.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject,Long> {
}
