package com.sarvesh.project.uber.uber.repositories;

import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query(value = """
    SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance
    FROM driver d
    WHERE d.available = true
      AND ST_DWithin(d.current_location, :pickupLocation, 10000)
    ORDER BY distance
    LIMIT 10
""", nativeQuery = true)
    List<Driver> findNearestMatchingDriver(@Param("pickupLocation") Point pickupLocation);

    @Query(value =
            "SELECT d.* " +
                    "FROM driver d " +
                    "WHERE d.available = true " +
                    "  AND ST_DWithin(d.current_location, :pickupLocation, 15000) " +
                    "ORDER BY d.rating DESC " +
                    "LIMIT 10",
            nativeQuery = true
    )
    List<Driver> findNearestByTopRatedDrivers(
            @Param("pickupLocation") Point pickupLocation
    );

    Optional<Driver> findByUser(User user);
}
