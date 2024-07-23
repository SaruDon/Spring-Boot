package com.fcrit.studentsexample.Student.Management.controllers;

import com.fcrit.studentsexample.Student.Management.entity.Professor;
import com.fcrit.studentsexample.Student.Management.entity.Student;
import com.fcrit.studentsexample.Student.Management.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    final private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(path = "/{professorId}")
    public Professor getProfessorById(@PathVariable Long professorId){
        return professorService.getProfessorById(professorId);
    }

    @PostMapping()
    public Professor createProfessor(@RequestBody Professor professor){
        return professorService.createProfessor(professor);
    }

    @PutMapping(path = "/{professorId}/subject/{subjectId}")
    public Professor assignProfessorToSubject(@PathVariable Long professorId,
                                          @PathVariable Long subjectId){
        return professorService.assignProfessorToSubject(professorId,subjectId);
    }

    @GetMapping
    public List<Professor> getAllProfessor(){
        return professorService.getAllProfessor();
    }


    @PutMapping(path = "/{professorId}/student/{studentId}")
    public Professor assignProfessorToStudent(@PathVariable Long professorId,
                                              @PathVariable Long studentId){
        return professorService.assignProfessorToStudent(professorId,studentId);
    }


}
