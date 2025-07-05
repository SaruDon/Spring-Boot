package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
