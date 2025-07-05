package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.DriverDto;
import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.DriverRepository;
import com.sarvesh.project.uber.uber.repositories.RideRepository;
import com.sarvesh.project.uber.uber.services.DriverService;
import com.sarvesh.project.uber.uber.services.RideRequestService;
import com.sarvesh.project.uber.uber.services.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private  final RideRepository rideRepository;
    private final DriverRepository driverRepository;


    private final RideRequestService riderRequestService;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = riderRequestService.findRideRequestById(rideRequestId);
        log.debug("rideRequest"+rideRequest);
        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("RideRequest already accepted by other driver");
        }
        log.debug("rideRequest"+rideRequest.getRideRequestStatus());
        Driver driver = getCurrentDriver();
        log.debug("Driver"+driver.getUser().getName()+driver.getAvailable());
        if (!driver.getAvailable()){
            throw  new RuntimeException("Driver Not available");
        }
        Ride ride =  rideService.createNewRide(rideRequest,driver);
        log.debug(">>>Ride"+ride);
        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId,Integer otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (driver==null){
            throw new RuntimeException("Driver not found");
        }
        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver does not belong to this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride Status not confimed"+ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Otp incorrect"+otp);
        }

        ride.setRideStatus(RideStatus.ONGOING);
        ride.setStartedAt(LocalDateTime.now());
        rideService.update(ride);
        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRider() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(20L).orElseThrow(()-> new ResourceNotFoundException("Driver not found"));
    }
}
