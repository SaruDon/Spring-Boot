package com.example.hospital.entity;

import com.example.hospital.entity.utils.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(unique = true)
    private BigInteger mobileNo;

    @Column(nullable = false)
    private String name;


    private Gender gender;

    @Column(nullable = false)
    private Integer age;

    @Column(unique = true)
    private String email;

    @OneToMany(
            mappedBy = "patients",
            orphanRemoval = true
    )
    Set<Appointment> appointments = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;


    //reports

    //bills
}
