package com.uber.Uber.services.impl;

import com.uber.Uber.dto.DriverDto;
import com.uber.Uber.dto.RideDto;
import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.dto.RiderDto;
import com.uber.Uber.entities.Driver;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.entities.Rider;
import com.uber.Uber.entities.User;
import com.uber.Uber.entities.enums.RideRequestStatus;
import com.uber.Uber.exception.ResourceNotFoundException;
import com.uber.Uber.repositories.RideRequestRepository;
import com.uber.Uber.repositories.RiderRepository;
import com.uber.Uber.services.RiderService;
import com.uber.Uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    private  final RideRequestRepository rideRequestRepository;
    private  final RiderRepository riderRepository;




    private final RideStrategyManager rideStrategyManager;




    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider= getCurrentRider();

        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);


        Double fare =  rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers =rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
        //TODO to sent req to all drivers

        return modelMapper.map(savedRideRequest,RideRequestDto.class);

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public Rider createRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return  riderRepository.save(rider);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider getCurrentRider() {
        //Todo implement spring securtiy

        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException("not fount with id 1 "+1));
    }
}
