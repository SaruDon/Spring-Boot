package com.example.springsecurity61.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
    private String message;
    private List<String> errorList;
    private HttpStatus httpStatus;
}
