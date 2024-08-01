package com.uber.Uber.services;

import com.uber.Uber.dto.DriverDto;
import com.uber.Uber.dto.RideDto;
import com.uber.Uber.dto.RiderDto;
import com.uber.Uber.entities.Driver;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String opt);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId,Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();


    Driver getCurrentDriver();
}

