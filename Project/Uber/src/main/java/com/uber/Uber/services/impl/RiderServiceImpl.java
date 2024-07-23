package com.uber.Uber.services.impl;

import com.uber.Uber.dto.DriverDto;
import com.uber.Uber.dto.RideDto;
import com.uber.Uber.dto.RideRequestDto;
import com.uber.Uber.dto.RiderDto;
import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.services.RiderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    public RiderServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        log.info(rideRequest.toString());
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
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
}
