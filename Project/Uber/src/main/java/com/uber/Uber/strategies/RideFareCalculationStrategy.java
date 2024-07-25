package com.uber.Uber.strategies;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.entities.RideRequest;

public interface RideFareCalculationStrategy {

     double  RIDE_FARE_MULTIPLIER =10;

    double calculateFare(RideRequest rideRequest);


}
