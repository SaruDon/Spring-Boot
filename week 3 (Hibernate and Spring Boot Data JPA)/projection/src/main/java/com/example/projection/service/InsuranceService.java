package com.example.projection.service;

import com.example.projection.dto.InsuranceDto;
import com.example.projection.entity.Insurance;
import com.example.projection.entity.Patient;
import com.example.projection.repository.InsuranceRepository;
import com.example.projection.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {


    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public InsuranceDto assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance);

        insurance.setPatient(patient);//optional to maintain bidirectional consistency

        patientRepository.save(patient);
        return modelMapper.map(insurance, InsuranceDto.class);
    }


}
