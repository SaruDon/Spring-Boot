package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

    public interface RideService {
    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto requestDto);

    Ride createNewRide(RideRequestDto requestDto, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRiderOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRiderOfDriver(Long driverId, PageRequest pageRequest);
}
