package com.example.Office.controller;

import com.example.Office.dto.SubjectDto;
import com.example.Office.services.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @GetMapping
    private ResponseEntity<List<SubjectDto>> getAllSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    private ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id){
        Optional<SubjectDto> subjectDto = subjectService.getSubjectById(id);
        return subjectDto
                .map(subjectDto1 -> ResponseEntity.ok(subjectDto1))
                .orElseThrow(()->new EntityNotFoundException("Subject Not found"));
    }

    @PostMapping
    private ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(subjectService.createSubject(subjectDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(subjectService.deleteById(id));
    }

    @PutMapping("/{subjectId}/assignProfessorASubject/{professorId}")
    private ResponseEntity<SubjectDto> assignProfessorASubject(@PathVariable Long subjectId,@PathVariable Long professorId){
        return ResponseEntity.ok(subjectService.assignProfessorASubject(subjectId,professorId));
    }
}
