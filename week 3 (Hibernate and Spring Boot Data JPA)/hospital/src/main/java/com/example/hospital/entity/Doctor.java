package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String department;

    @Column(unique = true)
    private BigInteger mobileNo;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    //appointments
    @OneToMany(
            mappedBy = "doctor"
    )
    Set<Appointment> appointments = new HashSet<>();
}
