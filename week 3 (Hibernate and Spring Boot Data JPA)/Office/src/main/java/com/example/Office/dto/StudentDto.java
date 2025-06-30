package com.example.Office.dto;


import com.example.Office.entity.Professor;
import com.example.Office.entity.Subject;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long student_id;

    private String name;

    private List<Subject> subjects;

    private List<Professor> professors;
}
