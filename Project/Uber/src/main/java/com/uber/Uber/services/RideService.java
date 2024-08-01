package com.uber.Uber.services;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.entities.Driver;
import com.uber.Uber.entities.Ride;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {


    Ride getRideById(Long rideId) ;

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long rideId, PageRequest pageRequest);

}