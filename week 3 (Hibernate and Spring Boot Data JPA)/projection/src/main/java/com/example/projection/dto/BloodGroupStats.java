package com.example.projection.dto;

import com.example.projection.entity.type.BloodGrpType;
import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class BloodGroupStats {
    private final BloodGrpType bloodGrpType;
    private final Long count;
}
