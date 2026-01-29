package com.example.projection.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer floor;

    private Integer capacity;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    private Set<Patient> patients= new HashSet<>();


    @ManyToOne()
    @JoinColumn(name = "manager_id")
    private Employee manager;
}
