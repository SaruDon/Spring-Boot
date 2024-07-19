package com.fcrit.onetoone.example.One.to.One.Many.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_department_id")
    @JsonIgnore
    private DepartmentEntity workerDepartment;


    @ManyToMany
    @JoinTable(
            name = "freelance_department_mapping",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private Set<DepartmentEntity> freelanceDepartments;




}
