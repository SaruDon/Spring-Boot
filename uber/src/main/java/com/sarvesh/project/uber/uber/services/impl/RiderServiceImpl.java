package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.*;
import com.sarvesh.project.uber.uber.entities.*;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.RiderRepository;
import com.sarvesh.project.uber.uber.services.*;
import com.sarvesh.project.uber.uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final RiderRepository riderRepository;

    private final RideService rideService;
    private final DriverService driverService;
    private final RideRequestService rideRequestService;
    private final RatingService ratingService;

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
        RideRequest savedRideRequest = rideRequestService.createNewRideRequest(rideRequest);
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
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        if (!ride.getRider().equals(rider)){
            throw new RuntimeException("Unauthorized Rider, rider does not own this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride in Ongoing you can only end ride now");
        }
        driverService.updateAvailability(ride.getDriver().getId(),true);
        return modelMapper.map(rideService.updateRideStatus(ride,RideStatus.CANCELLED), RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId,Double rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        Driver driver = ride.getDriver();
        if (!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not belong to this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.COMPLETED)) {
            throw new RuntimeException("Ride Status is not Completed can not rate" + ride.getRideStatus());
        }
        ratingService.rateDriver(driver,ride,rating);
        return modelMapper.map(driver,DriverDto.class);
    }

    @Override
    public RiderDto getMyProfile() {
        Rider rider = riderRepository.findById(getCurrentRider().getId()).orElseThrow(()-> new RuntimeException("Rider not found"));             ;
        return modelMapper.map(rider, RiderDto.class);
    }


    @Override
    public Page<RideDto> getAllMyRide(PageRequest pageRequest) {
        Rider rider = getCurrentRider();  // Assume this gets the logged-in Rider
        Page<Ride> ridesPage = rideService.getAllRiderOfRider(rider.getId(),pageRequest);
        return ridesPage.map(ride -> modelMapper.map(ride, RideDto.class));
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
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return riderRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Rider not associated with user with id:"+ user.getId()));
    }

    @Override
    public Rider getRiderById(Long riderId) {
        return riderRepository.findById(riderId).orElseThrow(()->new ResourceNotFoundException("Rider not found"));
    }


}
