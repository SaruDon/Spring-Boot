package com.uber.Uber.dto;

import com.uber.Uber.entities.Rider;
import com.uber.Uber.entities.enums.PaymentMethod;
import com.uber.Uber.entities.enums.RideRequestStatus;
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
public class RideRequestDto {
    private Long id;


    private PointDto pickupLocation;

    private PointDto dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestedTime;

    private RiderDto rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;
}
