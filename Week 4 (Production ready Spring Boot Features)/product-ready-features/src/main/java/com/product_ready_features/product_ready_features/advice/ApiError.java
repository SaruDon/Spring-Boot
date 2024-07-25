package com.product_ready_features.product_ready_features.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {


    public ApiError() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus httpStatus) {
        this();
        this.error = error;
        this.httpStatus = httpStatus;
    }

    private LocalDateTime localDateTime;
    private String error;
    private HttpStatus httpStatus;
}
