package com.example.Office.dto;

import com.example.Office.entity.Professor;
import com.example.Office.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private Long subject_id;

    private String title;

    private Professor professor;

    private List<Student> students;
}
