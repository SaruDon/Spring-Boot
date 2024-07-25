package com.uber.Uber.repositories;

import com.uber.Uber.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

//ST_Distance(point 1, point 2) distance between point 1 and point 2
//ST_DWithin(point1 ,10000)    distance within 10Km


    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance" +
            "FROM drivers d "+
            "WHERE d.available = true And ST_DWithin(d.current_location , :pickupLocation, 10000 )" +
            "ORDER BY distance" +
            "LIMIT 10"
    ,nativeQuery = true)
    List<Driver> findTenNearestMatchingDriver(Point pickupLocation);
}
