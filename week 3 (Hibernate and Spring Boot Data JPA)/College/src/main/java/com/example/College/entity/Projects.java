package com.example.College.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    private String title;

    @ManyToMany(mappedBy = "projects",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Employee> members;
}
