package com.sarvesh.project.uber.uber.dto;

import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.enums.PaymentMethod;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RideRequestDto {
    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDto rider; // one rider can have multiple ride request

    private RideRequestStatus rideRequestStatus;

    private PaymentMethod paymentMethod;

    private Double fare;
}
