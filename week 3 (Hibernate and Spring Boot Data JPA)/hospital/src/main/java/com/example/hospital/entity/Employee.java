package com.example.hospital.entity;

import com.example.hospital.entity.utils.Gender;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private BigInteger mobileNo;

    @Column(nullable = false)
    private String address;


    private Gender gender;

    @Column(nullable = false)
    private Integer salary;

    @Column(nullable = false, unique = true)
    private String email;


}
