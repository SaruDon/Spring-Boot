package com.sarvesh.SecurityApp.SecurityApplication.advices;

import com.sarvesh.SecurityApp.SecurityApplication.exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        log.error("Resource not found: {}", resourceNotFoundException.getMessage(), resourceNotFoundException);
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiError> handleInternalServerError(Exception exception) {
        log.error("Internal server error occurred: {}", exception.getMessage(), exception);
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An internal error occurred: " + exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationErrors(MethodArgumentNotValidException exception) {
        log.error("Validation error: {}", exception.getMessage(), exception);
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("input validation failed")
                .SubErrors(errors)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationError(AuthenticationException e) {
        log.error("Authentication error: {}", e.getMessage(), e);
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("Authentication failed: " + e.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(org.springframework.security.authentication.BadCredentialsException e) {
        log.error("Bad credentials: {}", e.getMessage(), e);
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("Invalid credentials: " + e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException e){
        log.error("JWT error: {}", e.getMessage(), e);
        ApiError apiError =  ApiError.builder()
                .message("JWT error: " + e.getLocalizedMessage())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }
}