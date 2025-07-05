package com.sarvesh.project.uber.uber.config;

import com.sarvesh.project.uber.uber.dto.PointDto;
import com.sarvesh.project.uber.uber.util.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();

        // PointDto to Point converter
        mapper.typeMap(PointDto.class, Point.class).setConverter(converter -> {
            PointDto pointDto = converter.getSource();
            if (pointDto == null) {
                return null;
            }
            return GeometryUtil.createPoint(pointDto);
        });

        // Point to PointDto converter
        mapper.typeMap(Point.class, PointDto.class).setConverter(context -> {
            Point point = context.getSource();
            if (point == null) {
                return null;
            }
            double[] coordinates = {
                    point.getX(),
                    point.getY()
            };
            return new PointDto(coordinates);
        });

        return mapper;
    }
}