package com.sarvesh.project.uber.uber.util;

import com.sarvesh.project.uber.uber.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);

    public static Point createPoint(PointDto pointDto) {
        if (pointDto == null) {
            throw new IllegalArgumentException("PointDto cannot be null");
        }

        double[] coordinates = pointDto.getCoordinates();
        if (coordinates == null || coordinates.length < 2) {
            throw new IllegalArgumentException("Coordinates array must contain at least 2 elements (longitude, latitude)");
        }

        Coordinate coordinate = new Coordinate(coordinates[0], coordinates[1]);
        return GEOMETRY_FACTORY.createPoint(coordinate);
    }
}