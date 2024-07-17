package com.uber.Uber.strategies;

import com.uber.Uber.dto.RideRequestDto;

public interface RideFairCalculationStrategy {

    double calculateFair(RideRequestDto rideRequestDto);


}
