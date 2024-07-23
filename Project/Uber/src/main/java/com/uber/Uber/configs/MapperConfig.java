package com.uber.Uber.configs;

import com.uber.Uber.dto.PointDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper getModelMapper(){

        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(PointDto.class, PointDto.class).setConverter(converter ->{
            PointDto  pointDto = converter.getSource();
        })

        return  new ModelMapper();
    }
}
