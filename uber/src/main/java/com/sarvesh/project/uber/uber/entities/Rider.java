package com.sarvesh.project.uber.uber.entities;


import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.awt.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

}
