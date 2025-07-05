package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.User;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto requestDto);

    RideDto cancelRide(Long rideId);

    RideDto rateDriver(Long rideId, Integer rating);

    RideDto getMyProfile();

    List<RideDto> getAllMyRider();

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
