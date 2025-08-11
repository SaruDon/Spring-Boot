package com.sarvesh.SecurityApp.SecurityApplication.advices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    public HttpStatus httpStatus;
    public String message;
    public List<String> SubErrors;
}
