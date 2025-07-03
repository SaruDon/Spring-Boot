package com.example.freeCurrencyApi.advices;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;

    private List<ApiError> apiErrors;

    public ApiResponse(LocalDateTime timeStamp) {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(List<ApiError> apiErrors) {
        this.apiErrors = apiErrors;
    }

    public ApiResponse(T data) {
        this.data = data;
    }
}
