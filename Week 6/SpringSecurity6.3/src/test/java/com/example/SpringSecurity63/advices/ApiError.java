package com.example.SpringSecurity62.advices;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
public class ApiError {
    private HttpStatus httpStatus;
    private String message;
    private List<String> errorList;
}
