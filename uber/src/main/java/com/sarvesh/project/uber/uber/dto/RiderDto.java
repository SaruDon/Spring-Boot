package com.sarvesh.project.uber.uber.dto;

import com.sarvesh.project.uber.uber.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RiderDto {

    private Long id;

    private UserDto user;

    private Double rating;
}
