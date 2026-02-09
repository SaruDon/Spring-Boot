package com.example.SpringSecurity57.advices;

import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime localDateTime;
    private T data;
    private ApiError apiError;

    public ApiResponse() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }
}
