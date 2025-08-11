package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.RideRequest;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.RideRepository;
import com.sarvesh.project.uber.uber.repositories.RideRequestRepository;
import com.sarvesh.project.uber.uber.repositories.RiderRepository;
import com.sarvesh.project.uber.uber.services.RideRequestService;
import com.sarvesh.project.uber.uber.services.RideService;
import com.sarvesh.project.uber.uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RideServiceImp implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(()-> new ResourceNotFoundException("Ride not found with Id"+rideId));
    }

    @Override
    @Transactional
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRM);
        Ride ride = Ride.builder()
                .rideStatus(RideStatus.CONFIRMED)
                .driver(driver)
                .rider(rideRequest.getRider())
                .fare(rideRequest.getFare())
                .dropOffLocation(rideRequest.getDropOffLocation())
                .pickUpLocation(rideRequest.getPickUpLocation())
                .paymentMethod(rideRequest.getPaymentMethod())
                .otp(generateOtp())
                .build();


        rideRequestRepository.findById(rideRequest.getId()).orElseThrow(()-> new ResourceNotFoundException("Ride req with this id not found"+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);

        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRiderOfRider(Long riderId, PageRequest pageRequest) {
        Rider rider = riderRepository.findById(riderId).orElseThrow(()->new ResourceNotFoundException("Rider not found"));

        return rideRepository.findAllByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findAllByDriver(driver,pageRequest);
    }


    @Override
    public void update(Ride ride) {
        if (ride.getId()==null){
            throw  new ResourceNotFoundException("Ride does not exist");
        }
        rideRepository.findById(ride.getId()).orElseThrow(()-> new ResourceNotFoundException("Ride  with this id not found"+ride.getId()));
        rideRepository.save(ride);
    }

    private Integer generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);  // Generates a number between 1000 and 9999
        return otp;
    }

}
