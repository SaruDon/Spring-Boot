package com.zomato.Zomato.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.awt.*;
import java.time.LocalTime;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean available;

    private String name;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point address;

    @OneToOne
    private Menu menu;

    private LocalTime openTime;

    private LocalTime closingTime;

    private String description;

    private Double rating;

}
