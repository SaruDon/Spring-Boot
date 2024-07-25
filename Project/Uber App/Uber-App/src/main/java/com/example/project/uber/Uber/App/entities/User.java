package com.example.project.uber.Uber.App.entities;


import com.example.project.uber.Uber.App.entities.enums.Role;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.Set;

@Entity
@Table(name ="app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
