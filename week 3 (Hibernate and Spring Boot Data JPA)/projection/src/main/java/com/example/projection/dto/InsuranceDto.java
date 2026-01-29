package com.example.projection.dto;

import com.example.projection.entity.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDto {

        private String policyNumber;

        private String provider;
        private LocalDateTime validTill;

        private LocalDateTime createdAt;

        private Patient patient;
}
