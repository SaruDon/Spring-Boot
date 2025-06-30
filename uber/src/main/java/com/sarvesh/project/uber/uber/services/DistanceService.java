package com.sarvesh.project.uber.uber.services;

import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

public interface DistanceService {
    double calculateDistance(Point src ,Point dest);
}
