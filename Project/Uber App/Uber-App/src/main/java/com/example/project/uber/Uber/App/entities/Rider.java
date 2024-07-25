package com.example.project.uber.Uber.App.entities;


import jakarta.persistence.*;

import java.sql.Driver;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    private Driver driver;

    private Double rating;
}
