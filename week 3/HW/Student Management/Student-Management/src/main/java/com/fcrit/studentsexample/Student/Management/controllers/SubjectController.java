package com.fcrit.studentsexample.Student.Management.controllers;

import com.fcrit.studentsexample.Student.Management.entity.Professor;
import com.fcrit.studentsexample.Student.Management.entity.Student;
import com.fcrit.studentsexample.Student.Management.entity.Subject;
import com.fcrit.studentsexample.Student.Management.repository.SubjectRepo;
import com.fcrit.studentsexample.Student.Management.service.ProfessorService;
import com.fcrit.studentsexample.Student.Management.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    final private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(path = "/{subjectId}")
    public Subject getSubjectById(@PathVariable Long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @PostMapping()
    public Subject createSubject(@RequestBody Subject subject){
        return subjectService.createSubject(subject);
    }




}
