package com.example.production_feature.exception;

public class ResouceNotFoundException extends RuntimeException {
  public ResouceNotFoundException(String message) {
    super(message);
  }
}
