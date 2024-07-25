package com.uber.Uber.strategies.impl;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.strategies.RideFareCalculationStrategy;

public class RideFareDefaultCalculation implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
