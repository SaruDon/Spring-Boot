package com.zomato.Zomato.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name ="user_id")
    private User user;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point address;

    @OneToMany(mappedBy = "customer")
    private Set<Order> foodCart = new HashSet<>();
    //Todo foodKart
}
