package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.RideRequestRepository;
import com.sarvesh.project.uber.uber.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    private final ModelMapper modelMapper;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(()->new ResourceNotFoundException("RideRequest no found with id"+rideRequestId));
    }

    @Override
    public RideRequest update(RideRequest rideRequest) {
        if (rideRequest.getId()==null){
            throw  new ResourceNotFoundException("Ridereq does not exist");
        }
        rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()-> new ResourceNotFoundException("Ride req with this id not found"+rideRequest.getId()));
        return rideRequestRepository.save(rideRequest);
    }

    @Override
    public RideRequest createNewRideRequest(RideRequest rideRequest) {
        return rideRequestRepository.save(rideRequest);
    }
}
