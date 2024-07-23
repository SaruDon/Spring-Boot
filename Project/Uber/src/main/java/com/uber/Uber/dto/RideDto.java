package com.uber.Uber.dto;

import com.uber.Uber.entities.Driver;
import com.uber.Uber.entities.Rider;
import com.uber.Uber.entities.enums.PaymentMethod;
import com.uber.Uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Point;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long id;

    private PointDto pickupLocation;

    private PointDto dropOffLocation;

    private LocalDateTime createdTime;


    private RiderDto rider;


    private DriverDto driver;


    private PaymentMethod paymentMethod;


    private RideStatus rideStatus;

    private String otp;

    private Double fair;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
