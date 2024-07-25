package com.uber.Uber.strategies.impl;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.services.DistanceService;
import com.uber.Uber.services.impl.DistanceServiceOSRMImpl;
import com.uber.Uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {


    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());
        return  distance*RIDE_FARE_MULTIPLIER;
    }
}
