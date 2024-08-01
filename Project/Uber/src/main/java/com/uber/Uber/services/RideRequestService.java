package com.uber.Uber.services;

import com.uber.Uber.entities.RideRequest;
import org.springframework.stereotype.Service;

@Service
public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
