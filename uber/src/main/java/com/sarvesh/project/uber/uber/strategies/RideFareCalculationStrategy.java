package com.sarvesh.project.uber.uber.strategies;

import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.RideRequest;

public interface RideFareCalculationStrategy {

    static final double RIDE_FARE_MULTIPLIER =10;

    double calculateFare(RideRequest rideRequest);
}
