package com.sarvesh.project.uber.uber.services;

import com.sarvesh.project.uber.uber.dto.*;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto requestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId,Double rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRide(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();

    Rider getRiderById(Long riderId);
}
