package com.sarvesh.project.uber.uber.entities;


import com.sarvesh.project.uber.uber.entities.enums.PaymentMethod;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RideRequest {
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

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Double Fare;


}
