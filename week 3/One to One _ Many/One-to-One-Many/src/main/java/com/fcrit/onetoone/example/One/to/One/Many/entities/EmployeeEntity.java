package com.fcrit.onetoone.example.One.to.One.Many.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;

    @ManyToMany
    @JoinTable(
            name = "freelance_department_mapping",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private Set<DepartmentEntity> freelanceDepartments;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_department_id", referencedColumnName = "id")
    @JsonIgnore
    private DepartmentEntity workerDepartment;

}
