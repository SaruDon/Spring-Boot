package com.uber.Uber.strategies.impl;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.entities.Driver;
import com.uber.Uber.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
