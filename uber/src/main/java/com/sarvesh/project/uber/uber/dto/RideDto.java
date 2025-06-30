package com.sarvesh.project.uber.uber.dto;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.enums.PaymentMethod;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {

    private Long id;

    private Point pickUpLocation;

    private Point dropOffLocation;

    private LocalDateTime createdTime;// Time at which ride is created

    private RiderDto ride;

    private DriverDto driver;

    private RideStatus rideStatus;

    private PaymentMethod paymentMethod;

    private Double Fare;

    private LocalDateTime startedAt;// Time at which ride starts

    private LocalDateTime endedAt; // Time at which ride ends
}
