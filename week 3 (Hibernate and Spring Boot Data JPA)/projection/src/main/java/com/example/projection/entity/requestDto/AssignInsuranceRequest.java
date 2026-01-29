package com.example.projection.entity.requestDto;

import com.example.projection.entity.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignInsuranceRequest {
    private Insurance insurance;
    private Long patientId;
}
