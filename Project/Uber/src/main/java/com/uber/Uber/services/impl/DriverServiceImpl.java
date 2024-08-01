package com.uber.Uber.services.impl;

import com.uber.Uber.dto.DriverDto;
import com.uber.Uber.dto.RideDto;
import com.uber.Uber.dto.RiderDto;
import com.uber.Uber.entities.Driver;
import com.uber.Uber.entities.Ride;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.entities.enums.RideRequestStatus;
import com.uber.Uber.entities.enums.RideStatus;
import com.uber.Uber.exception.ResourceNotFoundException;
import com.uber.Uber.repositories.DriverRepository;
import com.uber.Uber.services.DriverService;
import com.uber.Uber.services.RideRequestService;
import com.uber.Uber.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final RideRequestService rideRequestService;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw  new RuntimeException("Ride request can not be accepted"+ rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();

        if (!currentDriver.getAvailable()){
            throw new RuntimeException("Driver Not available");
        }

        currentDriver.setAvailable(false);
        Driver driver = driverRepository.save(currentDriver);

        Ride ride = rideService.createNewRide(rideRequest,driver);
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())){
            throw  new RuntimeException("Driver do not match");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw  new RuntimeException("Ride not confirmed"+ride.getRideStatus());
        }
        if (!otp.equals(ride.getOtp())){
            throw  new RuntimeException("Otp does'nt match");
        }

        ride.setStartedAt(LocalDateTime.now());
       Ride savedRide = rideService.updateRideStatus(ride,RideStatus.ONGOING);
        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()->
                new ResourceNotFoundException("Current driver not found"+2));
    }


}
