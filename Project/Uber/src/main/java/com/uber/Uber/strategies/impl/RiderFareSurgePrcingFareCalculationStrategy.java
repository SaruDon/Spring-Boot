package com.uber.Uber.strategies.impl;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.strategies.RideFairCalculationStrategy;

public class RiderFareSurgePrcingFareCalculationStrategy implements RideFairCalculationStrategy {
    @Override
    public double calculateFair(RideRequestDto rideRequestDto) {
        return 0;
    }
}