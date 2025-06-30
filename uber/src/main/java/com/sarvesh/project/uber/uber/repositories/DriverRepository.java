package com.sarvesh.project.uber.uber.repositories;

import com.sarvesh.project.uber.uber.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query(value = """
    SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance
    FROM driver d
    WHERE d.available = true
      AND ST_DWithin(d.current_location, :pickupLocation, 1000)
    ORDER BY distance
    LIMIT 10
""", nativeQuery = true)
    List<Driver> findNearestMatchingDriver(@Param("pickupLocation") Point pickupLocation);

}
