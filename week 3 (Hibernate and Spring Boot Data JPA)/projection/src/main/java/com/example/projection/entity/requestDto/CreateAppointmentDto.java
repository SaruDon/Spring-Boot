package com.example.projection.entity.requestDto;

import com.example.projection.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentDto {
    private Appointment appointment;

    private Long patientId;

    private Long doctorId;
}
