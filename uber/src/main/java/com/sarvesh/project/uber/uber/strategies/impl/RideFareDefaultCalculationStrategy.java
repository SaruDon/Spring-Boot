package com.sarvesh.project.uber.uber.strategies.impl;

import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.services.impl.DistanceServiceOSRMImpl;
import com.sarvesh.project.uber.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareDefaultCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceServiceOSRMImpl distanceServiceOSRM;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceServiceOSRM.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;

    }
}
