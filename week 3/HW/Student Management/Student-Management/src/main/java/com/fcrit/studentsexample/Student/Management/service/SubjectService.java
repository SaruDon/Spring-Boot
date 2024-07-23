package com.fcrit.studentsexample.Student.Management.service;

import com.fcrit.studentsexample.Student.Management.entity.Professor;
import com.fcrit.studentsexample.Student.Management.entity.Subject;
import com.fcrit.studentsexample.Student.Management.repository.ProfessorRepo;
import com.fcrit.studentsexample.Student.Management.repository.SubjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    final private SubjectRepo subjectRepo;

    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }


    
    public Subject getSubjectById(Long subjectId) {
        return subjectRepo.findById(subjectId).orElse(null);
    }


    public Subject createSubject(Subject subject) {
        return subjectRepo.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepo.findAll();
    }
}
