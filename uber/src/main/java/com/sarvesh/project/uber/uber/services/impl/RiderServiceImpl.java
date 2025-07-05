package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.RideRequestRepository;
import com.sarvesh.project.uber.uber.repositories.RiderRepository;
import com.sarvesh.project.uber.uber.services.RiderService;
import com.sarvesh.project.uber.uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    private final ModelMapper modelMapper;

    private final RideStrategyManager rideStrategyManager;

    Logger log = LoggerFactory.getLogger(RiderServiceImpl.class);


    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        // Get current rider
        Rider rider = getCurrentRider();
        log.debug("Got Rider: {}", rider);

        // Map DTO to entity
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        log.debug("Converted to rideRequest: {}", rideRequest);

        // Set the rider (this was missing!)
        rideRequest.setRider(rider);
        log.debug("Set rider on request: {}", rideRequest.getRider());


        // Update status
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        log.debug("Ride status updated: {}", rideRequest.getRideRequestStatus());

        // Calculate fare
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        log.debug("Calculated fare: {}", fare);

        rideRequest.setFare(fare);
        log.debug("Set ride request fare: {}", rideRequest.getFare());

        // Save the ride request
        log.debug("To be Saved rider on request: {}", rideRequest);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        log.debug("Successfully saved ride request with ID: {}", savedRideRequest.getId());
        log.debug("Saved ride request details: {}", savedRideRequest);

        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findDriverMatchingStrategy(rideRequest);
        drivers.forEach(driver -> {
                System.out.println(">>>>>>"+driver.getUser().getName().toString());
        });

//      TODO: Send notification to all drivers about rideRequest



        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RideDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRider() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        //Todo; implement sting security
        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException("Rider with Id not found"));
    }


}
