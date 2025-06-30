package com.example.Office.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    @JsonFormat(pattern = "hh-mm-ss dd-mm-yyyy")
    private LocalDateTime timeStamp;

    private T data;

    private ApiError apiError;

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }
}
