package com.uber.Uber.strategies;

import com.uber.Uber.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.uber.Uber.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.uber.Uber.strategies.impl.RideFareSurgePricingFareCalculation;
import com.uber.Uber.strategies.impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {


    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFareSurgePricingFareCalculation rideFareSurgePricingFareCalculation;
    private final RiderFareDefaultFareCalculationStrategy riderFareDefaultFareCalculationStrategy;


    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if (riderRating>=4.8){
            return  driverMatchingHighestRatedDriverStrategy;
        }else{
            return driverMatchingNearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        //peak hrs 6pm tp 9pm
        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime) {
            return rideFareSurgePricingFareCalculation;
        } else {
            return riderFareDefaultFareCalculationStrategy;
        }
    }

}
