package com.sarvesh.SecurityApp.SecurityApplication.advices;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        List<String> allowedRoutes = List.of("/v3/api-docs", "/actuator", "/auth");

        boolean isAllowed = allowedRoutes
                .stream()
                .anyMatch(route -> request.getURI().getPath().contains(route));

        // Don't wrap ApiResponse, ApiError, or responses from allowed routes
        if (body instanceof ApiResponse<?> || body instanceof ApiError || isAllowed) {
            return body;
        }

        // Check if the response originates from an exception handler
        String className = returnType.getContainingClass().getSimpleName();
        if ("GlobalExceptionHandler".equals(className)) {
            return body;
        }

        return new ApiResponse<>(body);
    }
}