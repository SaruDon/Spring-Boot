package com.students.students.corner.controllers;

import com.students.students.corner.dto.StudentsDto;
import com.students.students.corner.entity.StudentsEntity;
import com.students.students.corner.services.StudentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;



@RestController
@RequestMapping("/students")
public class StudentController {

    final private StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<StudentsDto> getStudentById(@PathVariable Long studentId){
        Optional<StudentsDto> studentsDto = studentServices.getStudentById(studentId);
        return studentsDto.map(studentsDto1 -> ResponseEntity.ok(studentsDto1)).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<StudentsDto> createStudent(@RequestBody StudentsDto studentsDto){
        return ResponseEntity.ok(studentServices.createStudent(studentsDto));
    }


    @GetMapping
    public ResponseEntity<List<StudentsDto>> getAllStudents(){
        return ResponseEntity.ok(studentServices.getAllStudents());
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<StudentsDto> updateStudentById(@RequestBody StudentsDto studentsDto, @PathVariable Long studentId){
        return ResponseEntity.ok(studentServices.updateStudentById(studentsDto,studentId));
    }

    @PatchMapping(path = "/{studentId}")
    public ResponseEntity<StudentsDto> updateFieldsByID(@PathVariable Long studentId, @RequestBody Map<String, Object>updates){
        return ResponseEntity.ok(studentServices.updateFieldsById(studentId,updates));
    }

}
