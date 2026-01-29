package com.example.projection.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentTime;

    private String reason;

    private String status;

    @ManyToOne()
    @JsonManagedReference  // Add this
    @JoinColumn(nullable = false)
    private Patient patient;

    @ManyToOne()
    @JoinColumn(nullable = false)
    @JsonManagedReference
    private Doctor doctor;
}
