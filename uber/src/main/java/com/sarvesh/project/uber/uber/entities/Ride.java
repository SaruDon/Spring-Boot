package com.sarvesh.project.uber.uber.entities;

import com.sarvesh.project.uber.uber.entities.enums.PaymentMethod;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "Geometry(Point,4326)")
    Point pickUpLocation;

    @Column(columnDefinition = "Geometry(Point,4326)")
    Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider ride;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Double Fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
