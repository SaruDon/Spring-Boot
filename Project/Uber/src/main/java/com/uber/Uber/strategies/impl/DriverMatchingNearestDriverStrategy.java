package com.uber.Uber.strategies.impl;


import com.uber.Uber.entities.Driver;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.repositories.DriverRepository;
import com.uber.Uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {


    private final DriverRepository driverRepository;


    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        //creating custom query
        return driverRepository.findTenNearestMatchingDriver(rideRequest.getPickupLocation());
    }
}
