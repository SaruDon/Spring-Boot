package com.uber.Uber.configs;


import com.uber.Uber.dto.PointDto;
import com.uber.Uber.utils.GeometryUntil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {



    @Bean
    public ModelMapper getModelMapper(){

        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(PointDto.class, Point.class).setConverter(converter ->{
            PointDto  pointDto = converter.getSource();
            return GeometryUntil.createPoint(pointDto);
        });

        mapper.typeMap(Point.class,PointDto.class).setConverter(context ->{
            Point point = context.getSource();
            double coordinates[] = {
                    point.getX(),
                    point.getY()
            };
            return new PointDto(coordinates);
        });

        return mapper;
    }
}
