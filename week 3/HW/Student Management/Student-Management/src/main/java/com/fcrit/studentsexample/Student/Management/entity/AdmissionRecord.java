package com.fcrit.studentsexample.Student.Management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer frees;

    @OneToOne
//    @JoinColumn(name = "student_info")
    private Student student;

}
