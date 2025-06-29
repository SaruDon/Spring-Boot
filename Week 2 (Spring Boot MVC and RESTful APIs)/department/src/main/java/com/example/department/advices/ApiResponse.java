package com.example.department.advices;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime date;
    private T data;
    private ApiError apiError;

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse() {
        this.date = LocalDateTime.now();
    }
}
