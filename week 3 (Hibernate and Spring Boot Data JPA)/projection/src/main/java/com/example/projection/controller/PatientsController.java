package com.example.projection.controller;


import com.example.projection.dto.BloodGroupStats;
import com.example.projection.dto.CPatientInfo;
import com.example.projection.dto.IPatientInfo;
import com.example.projection.dto.PatientDto;
import com.example.projection.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/patient")
@RequiredArgsConstructor
public class PatientsController {


    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping(path = "/info")
    public List<IPatientInfo> getAllPatientInfo(){
        return  patientService.getAllPatientsInfo();
    }

    @GetMapping(path = "/concrete")
    public List<CPatientInfo> getAllPatientInfoConcrete(){
        return  patientService.getAllPatientsInfoConcrete();
    }

    @GetMapping(path = "/stats")
    public List<BloodGroupStats> getBloodGroupStats(){
        return  patientService.getBloodGroupStats();
    }
}
