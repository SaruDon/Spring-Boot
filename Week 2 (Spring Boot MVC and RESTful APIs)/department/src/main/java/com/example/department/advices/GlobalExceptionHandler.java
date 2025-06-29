package com.example.department.advices;

import com.example.department.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFound exception){
        ApiError apiError =
                ApiError
                        .builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception) {
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationErrors(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(err ->err.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("input validation failed")
                .errors(errors)
                .build();
        return  new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);

    }
}
