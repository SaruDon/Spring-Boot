package com.product_ready_features.product_ready_features.config;

import com.product_ready_features.product_ready_features.auth.AuditorAwareImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAware")
public class AppConfig {

    @Bean
    ModelMapper getModelMapper(){
        return  new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditorAware(){
        return new AuditorAwareImplementation();
    }

}
