package com.fcrit.studentsexample.Student.Management.controllers;

import com.fcrit.studentsexample.Student.Management.entity.Professor;
import com.fcrit.studentsexample.Student.Management.entity.Student;
import com.fcrit.studentsexample.Student.Management.repository.StudentRepository;
import com.fcrit.studentsexample.Student.Management.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentsController {

    final private StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/{studentId}")
    public Student getStudentById(@PathVariable Long studentId){
        return studentService.getReferenceById(studentId);
    }

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping(path = "/{studentId}/subject/{subjectId}")
    public Student assignStudentToSubject(@PathVariable Long studentId,
                                            @PathVariable Long subjectId){
        return studentService.assignStudentToSubject(studentId,subjectId);
    }

}
