package com.uber.Uber.services.impl;

import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.entities.Driver;
import com.uber.Uber.entities.Ride;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.entities.enums.RideRequestStatus;
import com.uber.Uber.entities.enums.RideStatus;
import com.uber.Uber.exception.ResourceNotFoundException;
import com.uber.Uber.repositories.RideRepository;
import com.uber.Uber.services.RideRequestService;
import com.uber.Uber.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;

    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {

        return  rideRepository.findById(rideId).orElseThrow(()-> new ResourceNotFoundException("No ride id"));
    }

    @Override
    public void matchWithDrivers(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest,Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateOtp());
        ride.setId(null);

        rideRequestService.update(rideRequest);
         return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long rideId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long rideId, PageRequest pageRequest) {
        return null;
    }


    private String generateOtp(){
        Random random = new Random();
        int optInt = random.nextInt(10000);
        return  String.format("%04d",optInt);
    }
}
