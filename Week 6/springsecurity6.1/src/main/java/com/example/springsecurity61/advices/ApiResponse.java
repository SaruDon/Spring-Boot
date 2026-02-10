package com.example.springsecurity61.advices;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class ApiResponse <T>{
    private ApiError apiError;
    private T data;
    private LocalDateTime localDateTime;

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
