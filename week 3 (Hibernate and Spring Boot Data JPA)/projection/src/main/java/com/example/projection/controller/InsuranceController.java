package com.example.projection.controller;

import com.example.projection.dto.InsuranceDto;
import com.example.projection.entity.Insurance;
import com.example.projection.entity.requestDto.AssignInsuranceRequest;
import com.example.projection.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insurance")
public class InsuranceController {


    private final InsuranceService insuranceService;



    @PostMapping(path = "/assign")
    public InsuranceDto assignInsuranceToPatient(@RequestBody AssignInsuranceRequest request){
       return insuranceService.assignInsuranceToPatient(request.getInsurance(),request.getPatientId());
    }

}
