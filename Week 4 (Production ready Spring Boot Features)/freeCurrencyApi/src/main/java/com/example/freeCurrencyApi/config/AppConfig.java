package com.example.freeCurrencyApi.config;


import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.rmi.ServerError;
import java.rmi.ServerException;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class AppConfig {

    @Value("${currencyExchange.base.url}")
    private String BASE_URL;

    @Bean
    RestClient getCurrencyConversion(){
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,((request, response) -> {
                    throw new ServerException("server Error Occurred"+response.getBody());
                }))
                .build();
    }

}
