package com.example.SpringSecurity57.advices;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Builder
@Data
public class ApiError{
    private HttpStatus httpStatus;
    private String message;
    private List<String> errorList;
}
