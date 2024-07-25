package com.uber.Uber.services.impl;

import com.uber.Uber.services.DistanceService;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        //calculate 3rd party api called osrm to get me the distance

        return 0;
    }
}

