package com.example.Office.controller;


import com.example.Office.dto.StudentDto;
import com.example.Office.entity.Student;
import com.example.Office.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {


    private  final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/hello")
    private String hello(){
        return "Hello";
    }

    @GetMapping
    private ResponseEntity<List<StudentDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    private ResponseEntity<StudentDto> getStudentsById(@PathVariable Long id){
        Optional<StudentDto> student = studentService.getStudentsById(id);
        return student
                .map(studentDto -> ResponseEntity.ok(studentDto))
                .orElse(null);

    }

    @PostMapping
    private ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.createStudent(studentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deleteById(id));
    }

    @PutMapping("/{id}")
    private ResponseEntity<StudentDto> updateById(@RequestBody StudentDto studentDto,@PathVariable Long id){
        return ResponseEntity.ok(studentService.updateById(studentDto,id));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<StudentDto> partialUpdatesById(@PathVariable Long id, @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(studentService.partialUpdatesById(id,updates));
    }

    @PutMapping("/{id}/addSubject/{subjectId}")
    private ResponseEntity<StudentDto> addSubjectToStudent(@PathVariable Long id, @PathVariable Long subjectId){
        return ResponseEntity.ok(studentService.addSubjectToStudent(id,subjectId));
    }
}
