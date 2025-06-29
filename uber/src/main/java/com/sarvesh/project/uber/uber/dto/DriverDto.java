package com.sarvesh.project.uber.uber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {
    private UserDto userDto;
    private Double ratring;
}
