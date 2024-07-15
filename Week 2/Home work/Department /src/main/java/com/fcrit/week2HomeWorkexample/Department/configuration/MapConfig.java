package com.fcrit.week2HomeWorkexample.Department.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }
}
