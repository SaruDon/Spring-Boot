package com.fcrit.studentsexample.Student.Management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="Professor_id")
    private Professor professor;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "student_and_subject_",
    joinColumns = @JoinColumn(name = "subject"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentList;

}
