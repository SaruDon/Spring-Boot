package com.uber.Uber.strategies;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.entities.Driver;
import com.uber.Uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest RideRequest);
}
