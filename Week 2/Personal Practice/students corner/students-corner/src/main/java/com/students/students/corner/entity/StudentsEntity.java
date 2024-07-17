package com.students.students.corner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "Students")
@Entity
@Data
public class StudentsEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long studentId;

    private String name;
    private Long rollNo;
    private Character division;
    private LocalDateTime dateOfJoining;
    private String password;
}
