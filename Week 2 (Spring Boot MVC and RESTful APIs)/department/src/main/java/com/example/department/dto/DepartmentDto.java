package com.example.department.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;

    @NonNull
    @NotBlank
    @NotEmpty
    @Size(min=3, max = 10, message = "No of char should be in range 3 to 10")
    @NotNull(message = "Required field name")
    private String title;

    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;

    @Digits(integer = 6,fraction = 2, message = "Salary can be in for of XXXXXX.YY")
    @Positive
    private Double avgSalary;

    @Positive
    @Max(value = 80, message = "age can not be greater than 80")
    @Min(value = 18, message = "age can not be less than 19")
    private Integer teamSize;

    @NonNull
    @NotBlank
    @NotEmpty
    @Size(min=3, max = 10, message = "No of char should be in range 3 to 10")
    @NotNull(message = "Required field name")
    private String managerName;

    @PastOrPresent(message = "Date of joining can not in future")
    private LocalDateTime createdAt;
}
