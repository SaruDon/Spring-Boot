package com.example.projection.service;

import com.example.projection.dto.BloodGroupStats;
import com.example.projection.dto.CPatientInfo;
import com.example.projection.dto.IPatientInfo;
import com.example.projection.dto.PatientDto;
import com.example.projection.entity.Patient;
import com.example.projection.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public List<PatientDto> getAllPatients(){
        List<Patient> patientList = patientRepository.findAll();
        return patientList
                .stream()
                .map(p -> modelMapper.map(p, PatientDto.class))
                .toList();
    }

    public List<IPatientInfo> getAllPatientsInfo(){
        return patientRepository.getAllPatientInfo();
    }

    public List<CPatientInfo> getAllPatientsInfoConcrete(){
        return patientRepository.getAllPatientInfoConcrete();
    }

    public List<BloodGroupStats> getBloodGroupStats(){
        return patientRepository.getBloodGroupStats();
    }


    @Transactional
    public void patientDelete(Long id){
         patientRepository.deleteById(id);
    }

    public PatientDto createPatient(PatientDto patientDto){
        Patient patientToSave = modelMapper.map(patientDto, Patient.class);
        return modelMapper.map(patientRepository.save(patientToSave),PatientDto.class);
    }

}
