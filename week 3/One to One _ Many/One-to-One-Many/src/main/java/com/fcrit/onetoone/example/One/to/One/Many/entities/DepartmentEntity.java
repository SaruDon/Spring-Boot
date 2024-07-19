package com.fcrit.onetoone.example.One.to.One.Many.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "departments")
@Entity
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    //creating 'manager' field of type EmployeeEntity inside DepartmentEntity
    @OneToOne
    @JoinColumn(name = "department_manager")
    private EmployeeEntity manager;

    @OneToMany(mappedBy = "workerDepartment",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<EmployeeEntity> workers;

    @ManyToMany(mappedBy = "freelanceDepartments")
    @JsonIgnore
    private Set<EmployeeEntity> freelancers;


}
