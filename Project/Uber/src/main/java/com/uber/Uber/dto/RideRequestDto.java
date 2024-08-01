package com.uber.Uber.dto;

import com.uber.Uber.entities.enums.PaymentMethod;
import com.uber.Uber.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private Long id;


    private PointDto pickupLocation;

    private PointDto dropOffLocation;
    private PaymentMethod paymentMethod;

    private LocalDateTime requestedTime;

    private RiderDto rider;

    private Double fare;

    private RideRequestStatus rideRequestStatus;

}
