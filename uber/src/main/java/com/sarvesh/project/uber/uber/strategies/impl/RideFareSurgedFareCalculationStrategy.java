package com.sarvesh.project.uber.uber.strategies.impl;

import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.strategies.RideFareCalculationStrategy;

public class RideFareSurgedFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
