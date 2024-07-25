package com.fcrit.studentsexample.Student.Management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "professor")
    private List<Subject> subjects;

    @ManyToMany(mappedBy = "professorsOfStudents")
    private List<Student> studentsList;

}
