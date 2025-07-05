package com.sarvesh.project.uber.uber.strategies;

import com.sarvesh.project.uber.uber.strategies.impl.DriverMatchingHighestRatingStrategy;
import com.sarvesh.project.uber.uber.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.sarvesh.project.uber.uber.strategies.impl.RideFareDefaultCalculationStrategy;
import com.sarvesh.project.uber.uber.strategies.impl.RideFareSurgedFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class RideStrategyManager {

    private final DriverMatchingHighestRatingStrategy driverMatchingHighestRatingStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFareDefaultCalculationStrategy rideFareDefaultCalculationStrategy;
    private final RideFareSurgedFareCalculationStrategy rideFareSurgedFareCalculationStrategy;


    Logger log = LoggerFactory.getLogger(RideStrategyManager.class);


    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if (riderRating >= 4.8) {
            log.debug("riderating>4.8"+riderRating);
            return driverMatchingHighestRatingStrategy;
        } else {
            return driverMatchingNearestDriverStrategy;
        }
    }


    public RideFareCalculationStrategy rideFareCalculationStrategy(){

        //Time between 6PM to 9PM

        LocalTime surgedStartTime = LocalTime.of(18, 0);
        LocalTime surgedEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurged = currentTime.isAfter(surgedStartTime) && currentTime.isBefore(surgedEndTime);

        if (isSurged) {
            log.debug("isSurger"+isSurged);
            return rideFareSurgedFareCalculationStrategy;
        } else {
            log.debug("isSurger"+isSurged);
            return rideFareDefaultCalculationStrategy;
        }
    }

}
