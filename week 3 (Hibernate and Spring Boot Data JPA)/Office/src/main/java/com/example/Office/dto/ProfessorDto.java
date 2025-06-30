package com.example.Office.dto;


import com.example.Office.entity.Student;
import com.example.Office.entity.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {
    private Long professor_id;

    private String name;

    private List<Subject> subjects;

    private List<Student> students;
}
