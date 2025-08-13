package com.sarvesh.project.uber.uber.controllers;

import com.sarvesh.project.uber.uber.dto.*;
import com.sarvesh.project.uber.uber.services.RiderService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")
@Slf4j
public class RiderController {

    private final RiderService riderService;

    Logger logger = LoggerFactory.getLogger(RiderController.class);


    @PostMapping("/requestRide")
    private ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        logger.debug("requestRIde called"+rideRequestDto);
        RideRequestDto requestDto = riderService.requestRide(rideRequestDto);
        return new ResponseEntity<>(requestDto, HttpStatus.CREATED);
    }

    @PatchMapping("/cancelRide/{rideId}")
    private ResponseEntity<RideDto> cancelRideById(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver")
    private ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto rateDto){
        return ResponseEntity.ok(riderService.rateDriver(rateDto.getRideId(),rateDto.getRating()));
    }

    @GetMapping("/getMyProfile")
    private ResponseEntity<RiderDto> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(
                pageOffset,
                pageSize,
                Sort.by(Sort.Direction.DESC,"createdTime","id")
        );
        return ResponseEntity.ok(riderService.getAllMyRide(pageRequest));
    }


}
