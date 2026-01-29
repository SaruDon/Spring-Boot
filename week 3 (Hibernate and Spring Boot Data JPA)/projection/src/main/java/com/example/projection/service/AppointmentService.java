package com.example.projection.service;

import com.example.projection.dto.AppointmentDto;
import com.example.projection.entity.Appointment;
import com.example.projection.entity.Doctor;
import com.example.projection.entity.Patient;
import com.example.projection.repository.AppointmentRepository;
import com.example.projection.repository.DoctorRepository;
import com.example.projection.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public AppointmentDto createNewAppointment(@NonNull Appointment appointment, Long patientId, Long doctorId){

        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        appointmentRepository.save(appointment);
        return modelMapper.map(appointment, AppointmentDto.class);
    }


}

