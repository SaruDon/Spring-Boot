package com.sarvesh.project.uber.uber.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(
                name = "idx_rating_rider"
                ,columnList = "rider_id"
        ),
        @Index(
                name = "idx_rating_driver"
                ,columnList = "driver_id"
        )
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    private Double driverRating;
    private Double riderRating;
}
