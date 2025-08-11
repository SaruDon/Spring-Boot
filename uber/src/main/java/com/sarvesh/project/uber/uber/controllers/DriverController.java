package com.sarvesh.project.uber.uber.controllers;

import com.sarvesh.project.uber.uber.dto.*;
import com.sarvesh.project.uber.uber.entities.Driver;
import com.sarvesh.project.uber.uber.repositories.DriverRepository;
import com.sarvesh.project.uber.uber.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@Secured("ROLE_DRIVER")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;



    @PostMapping("/acceptRide/{rideRequestId}")
    private ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    private ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto){
        return ResponseEntity.ok(driverService.startRide(rideRequestId,rideStartDto.getOtp()));
    }

    @PostMapping("/endRide/{rideId}")
    private ResponseEntity<RideDto> endRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.endRide(rideId));
    }


    @PostMapping("/rateRider")
    private ResponseEntity<RiderDto> rateRider(@RequestBody RatingDto rateDto){
        return ResponseEntity.ok(driverService.rateRider(rateDto.getRideId(),rateDto.getRating()));
    }

    @GetMapping("/getMyProfile")
    private ResponseEntity<DriverDto> getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize, Sort.by(Sort.Direction.DESC,"createdTime","id"));
        return ResponseEntity.ok(driverService.getAllMyRiders(pageRequest));
    }

    @PatchMapping("/cancelRide/{rideId}")
    private ResponseEntity<RideDto> cancelRideById(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

}
