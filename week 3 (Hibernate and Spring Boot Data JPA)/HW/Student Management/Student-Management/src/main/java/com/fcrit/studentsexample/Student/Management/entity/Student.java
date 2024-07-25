package com.fcrit.studentsexample.Student.Management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "professor_id",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professorsOfStudents;

    @ManyToMany(mappedBy = "studentList")
    private List<Subject> subjects;
}
