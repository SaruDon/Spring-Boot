package com.fcrit.week2HomeWorkexample.Department.dto;

import com.fcrit.week2HomeWorkexample.Department.annotations.SalaryValidation;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "field can not be Blank")
    @NotEmpty(message = "field can not be Empty")
    @NotNull(message = "field can not be null")

    private String title;

    @SalaryValidation
    private Long avgSalary;

    @AssertTrue(message = "value not true")
    private Boolean isActive;

    @PastOrPresent(message = "Date of joining can not in future")
    private LocalDateTime createdAt;
}
