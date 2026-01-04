package com.example.projection.dto;

import com.example.projection.entity.type.BloodGrpType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientDto {

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    private BloodGrpType bloodGrpType;

}
