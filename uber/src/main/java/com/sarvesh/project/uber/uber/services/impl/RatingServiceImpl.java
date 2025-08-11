package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Rating;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.Rider;
import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.repositories.DriverRepository;
import com.sarvesh.project.uber.uber.repositories.RatingRepository;
import com.sarvesh.project.uber.uber.repositories.RiderRepository;
import com.sarvesh.project.uber.uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;

    private final ModelMapper modelMapper;




    @Override
    public void rateDriver(Driver driver, Ride ride, Double rating) {
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("ratingObj not found"+ride.getId()));
        //update in rating table
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);//save ratingtable

        Double newRating = ratingRepository.findAllByDriver(driver)
                .stream()
                .mapToDouble(rating1-> rating1.getDriverRating())
                .average().orElse(0.0);
        driver.setRating(newRating);
        driverRepository.save(driver);
    }

    @Override
    public void rateRider(Rider rider, Ride ride, Double rating) {
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("ratingObj not found"+ride.getId()));
        //update in rating table
        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);//save ratingtable

        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(rating1-> rating1.getRiderRating())
                .average().orElse(0.0);
        rider.setRating(newRating);
        riderRepository.save(rider);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating =Rating
                .builder()
                .riderRating(0.0)
                .driverRating(0.0)
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }

}
