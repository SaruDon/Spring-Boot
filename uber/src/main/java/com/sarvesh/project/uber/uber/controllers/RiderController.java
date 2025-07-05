package com.sarvesh.project.uber.uber.controllers;

import com.sarvesh.project.uber.uber.dto.RideRequestDto;
import com.sarvesh.project.uber.uber.dto.RiderDto;
import com.sarvesh.project.uber.uber.services.RiderService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
@Slf4j
public class RiderController {

    private final RiderService riderService;

    Logger logger = LoggerFactory.getLogger(RiderController.class);


    @PostMapping("/requestRide")
    private ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        logger.debug("requestRIde called"+rideRequestDto);
        return new ResponseEntity<>(riderService.requestRide(rideRequestDto), HttpStatus.CREATED);
    }

}
