package com.uber.Uber.dto;

import com.uber.Uber.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDto {
    private Long id;

    private UserDto user;

    private Double rating;
}
