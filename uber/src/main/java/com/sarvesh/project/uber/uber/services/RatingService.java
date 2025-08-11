package com.sarvesh.project.uber.uber.services;


import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.Ride;
import com.sarvesh.project.uber.uber.entities.Rider;


public interface RatingService {

    void rateDriver(Driver driver, Ride ride, Double rating);
    void rateRider(Rider rider, Ride ride, Double Rating);

    void createNewRating(Ride ride);

}
