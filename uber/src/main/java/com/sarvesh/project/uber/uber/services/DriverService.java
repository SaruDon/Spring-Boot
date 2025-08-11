package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.dto.DriverDto;
import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.dto.RiderDto;
import com.sarvesh.project.uber.uber.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, Integer otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Double rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRiders(PageRequest pageRequest);

    void updateAvailability(Long driverId,Boolean isAvailable);

    Driver getCurrentDriver();

    Driver updateDriverRating(Driver driver);

    Driver onBoardNewDriver(Driver driver);
}
