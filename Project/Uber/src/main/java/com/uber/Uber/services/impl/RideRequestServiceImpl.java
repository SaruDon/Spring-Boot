package com.uber.Uber.services.impl;

import com.uber.Uber.entities.RideRequest;
import com.uber.Uber.exception.ResourceNotFoundException;
import com.uber.Uber.repositories.RideRequestRepository;
import com.uber.Uber.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(()->new ResourceNotFoundException("this requestId not found"+rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()->new ResourceNotFoundException("not fount requestId"));
        rideRequestRepository.save(toSave);
    }
}
