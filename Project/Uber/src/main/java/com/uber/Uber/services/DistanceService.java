package com.uber.Uber.services;

import org.geolatte.geom.Point;

public interface DistanceService {

    double calculateDistance(Point src,Point dest);
}
