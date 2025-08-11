package com.sarvesh.project.uber.uber.dto;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.enums.PaymentMethod;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {

    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime createdTime;// Time at which ride is created

    private RiderDto rider;

    private DriverDto driver;

    private RideStatus rideStatus;

    private PaymentMethod paymentMethod;

    private Double Fare;

    private LocalDateTime startedAt;// Time at which ride starts

    private Integer otp;

    private LocalDateTime endedAt; // Time at which ride ends
}
