package com.sarvesh.project.uber.uber.strategies.impl;

import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHighestRatingStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findDriverMatchingStrategy(RideRequest rideRequest) {
        return List.of();
    }
}
