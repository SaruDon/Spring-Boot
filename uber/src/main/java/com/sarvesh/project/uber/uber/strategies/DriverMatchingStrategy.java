package com.sarvesh.project.uber.uber.strategies;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findDriverMatchingStrategy(RideRequest rideRequest);
}
