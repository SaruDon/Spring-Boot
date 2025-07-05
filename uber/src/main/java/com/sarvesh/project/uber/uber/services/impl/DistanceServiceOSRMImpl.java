package com.sarvesh.project.uber.uber.services.impl;

import com.sarvesh.project.uber.uber.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API = "http://router.project-osrm.org/route/v1/driving/";
    //13.388860,52.517037;13.397634,52.529407;13.428555,52.523219?overview=false

    @Override
    public double calculateDistance(Point src, Point dest) {

        try {
            String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();
            OSRMResponseDto osrmResponseDto=  RestClient.builder()
                    .baseUrl(OSRM_API)
                    .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);
            return osrmResponseDto.getRoutes().get(0).getDistance() / 1000.0;
        }catch (Exception e){
            throw new RuntimeException("Error Getting Distance"+ e.getMessage());
        }

    }
}


@Data
class OSRMResponseDto{
    private List<OSRMRoutes> routes;
}

@Data
class OSRMRoutes{
    private Double distance;
}