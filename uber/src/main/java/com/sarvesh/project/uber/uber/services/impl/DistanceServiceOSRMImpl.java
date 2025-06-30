package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        //call 3rd party api
        return 0;
    }
}
