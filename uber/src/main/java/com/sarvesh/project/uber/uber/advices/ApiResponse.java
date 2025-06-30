package com.sarvesh.project.uber.uber.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T Data;
    private ApiError apiError;

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }

    public ApiResponse(T data) {
        this();
        Data = data;
    }

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }
}
