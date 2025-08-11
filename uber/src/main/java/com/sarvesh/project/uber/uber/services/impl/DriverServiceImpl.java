package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.dto.DriverDto;
import com.sarvesh.project.uber.uber.dto.RideDto;
import com.sarvesh.project.uber.uber.dto.RiderDto;
import com.sarvesh.project.uber.uber.entities.*;
import com.sarvesh.project.uber.uber.entities.enums.RideRequestStatus;
import com.sarvesh.project.uber.uber.entities.enums.RideStatus;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.DriverRepository;
import com.sarvesh.project.uber.uber.repositories.RiderRepository;
import com.sarvesh.project.uber.uber.services.*;
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

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;


    private final RideRequestService riderRequestService;
    private final RideService rideService;
    private final PaymentService paymentService;
    private final RatingService ratingService;

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
        driver.setAvailable(false);
        driverRepository.save(driver);
        Ride ride =  rideService.createNewRide(rideRequest,driver);
        log.debug(">>>Ride"+ride);
        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Unauthorized");
        }
        //Only allow to cancelRide if Ride Status is Confirmed
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be canceled now"+ride.getRideStatus());
        }

        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        updateAvailability(driver.getId(),true);

        return modelMapper.map(ride,RideDto.class);
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

        //update ride status
        ride.setRideStatus(RideStatus.ONGOING);
        ride.setStartedAt(LocalDateTime.now());
        rideService.update(ride);

        //create new payment
        paymentService.createNewPayment(ride);

        //create new rating
        ratingService.createNewRating(ride);

        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (driver==null){
            throw new RuntimeException("Driver not found");
        }
        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver does not belong to this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new RuntimeException("Ride Status is not onGoing it is" + ride.getRideStatus());
        }

        ride.setEndedAt(LocalDateTime.now());
        rideService.updateRideStatus(ride,RideStatus.COMPLETED);
        updateAvailability(driver.getId(),true);


        paymentService.processPayment(ride);

        rideService.update(ride);

        return modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Double rating) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver does not belong to this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.COMPLETED)) {
            throw new RuntimeException("Ride Status is not Completed can not rate" + ride.getRideStatus());
        }

        ratingService.rateRider(ride.getRider(),ride,rating);//rate rider
        Rider rider =ride.getRider();
        return modelMapper.map(rider,RiderDto.class);
    }

    @Override
    public DriverDto getMyProfile() {
        return modelMapper.map(getCurrentDriver(),DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRiders(PageRequest pageRequest) {
        Driver driver =getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver,pageRequest).map(ride -> modelMapper.map(ride,RideDto.class));
    }

    @Override
    public void updateAvailability(Long driverId,Boolean isAvailable) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(()-> new RuntimeException("Driver not found"));
        driver.setAvailable(isAvailable);
        driverRepository.save(driver);
    }


    @Override
    public Driver getCurrentDriver() {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driverRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Driver not associated with user with id:"+ user.getId()));
    }

    @Override
    public Driver updateDriverRating(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver onBoardNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
