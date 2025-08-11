package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    RideRequest update(RideRequest rideRequest);

    RideRequest createNewRideRequest(RideRequest rideRequest);
}
