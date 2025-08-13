package com.sarvesh.project.uber.uber.advices;

import com.sarvesh.project.uber.uber.exceptions.ResourceNotFoundException;
import com.sarvesh.project.uber.uber.exceptions.RuntimeConflictException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    private ResponseEntity<ApiError> handleRuntimeConflictException(RuntimeConflictException ex) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException e) {
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("Invalid email or password")
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiError> handleInternalServerError(Exception exception) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationErrors(MethodArgumentNotValidException exception) {
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
                .subErrors(errors)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationError(AuthenticationException e) {
        System.out.println("AuthenticationException caught: " + e.getClass().getName() + " - " + e.getMessage());
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("Authentication failed: " + e.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException e){
        ApiError apiError =  ApiError.builder()
                .message(e.getLocalizedMessage())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.FORBIDDEN)
                .message("Access Denied: You don't have permission to access this resource")
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
}