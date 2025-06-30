package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.User;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import com.sarvesh.project.uber.uber.repositories.RideRequestRepository;
import com.sarvesh.project.uber.uber.repositories.RiderRepository;
import com.sarvesh.project.uber.uber.services.RiderService;
import com.sarvesh.project.uber.uber.strategies.DriverMatchingStrategy;
import com.sarvesh.project.uber.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.locationtech.jts.geom.Dimension.L;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;


    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);

        //update status
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        //calculate fare
        Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest= rideRequestRepository.save(rideRequest);

        //driver matching
        driverMatchingStrategy.findDriverMatchingStrategy(rideRequest);

        return modelMapper.map(savedRideRequest,RideRequestDto.class);
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
}
