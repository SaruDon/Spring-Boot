package com.example.projection.dto;

import com.example.projection.entity.Doctor;
import com.example.projection.entity.Patient;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDto {

    private Long id;

    private LocalDateTime appointmentTime;

    private String reason;

    private String status;

    private Patient patient;

    private Doctor doctor;
}
