package com.example.Office.advices;

import com.example.Office.exception.ReSourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ReSourceNotFoundException.class)
    private ResponseEntity<ApiError> handleResourceNotFoundException(ReSourceNotFoundException exception){
        ApiError apiError =
                ApiError
                        .builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiError> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
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
                .subError(errors)
                .build();
        return  new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);

    }
}
