package com.uber.Uber.dto;

import com.uber.Uber.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {

    private Long id;


    private User user;

    private Double rating;

    private Boolean available;

    private String vehicleId;

    private PointDto currentLocation;
}
