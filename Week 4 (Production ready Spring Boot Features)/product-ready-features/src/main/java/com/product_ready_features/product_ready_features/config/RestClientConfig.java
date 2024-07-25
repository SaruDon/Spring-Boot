package com.product_ready_features.product_ready_features.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


// Create a RestClientConfigClass in config package
//Create Bean of RestClient

@Configuration
public class RestClientConfig {

    @Value("${employeeService.base.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("employeeRestClient") //to give unique identifier to RestApi
    RestClient getEmployeeServericeRestClient(){
        return RestClient.builder()
//                .baseUrl("http://localhost:8080") we should not hardcode values
                .baseUrl(BASE_URL)
                .defaultStatusHandler(HttpStatusCode::is4xxClientError,(req, res)->{
                    throw  new RuntimeException("Server error occurred");
                })
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE) //return of application should be in JSON form
                .build() ;

    }

}
