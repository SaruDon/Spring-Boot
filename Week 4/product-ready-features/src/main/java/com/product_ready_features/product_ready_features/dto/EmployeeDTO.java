package com.product_ready_features.product_ready_features.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class EmployeeDTO {
    private Long id;

    private String name;


    private String email;

    private String role;


    private Integer age;

    private Double salary;


    private LocalDate dateOfJoining;

    private Boolean isActive;
}
