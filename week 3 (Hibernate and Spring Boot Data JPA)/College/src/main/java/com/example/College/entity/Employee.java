package com.example.College.entity;

import com.example.College.entity.enums.EmpType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //Only for managers
    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private Department managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worker_department_id",referencedColumnName = "id")
    @JsonIgnore
    private Department workerDepartment;

    private EmpType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "freelance_department_mapping",
                joinColumns = @JoinColumn(name = "employee_id"),
                inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> freelances;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "employee_projects",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns =  @JoinColumn(name = "project_id")
    )
    private Set<Projects> projects;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
