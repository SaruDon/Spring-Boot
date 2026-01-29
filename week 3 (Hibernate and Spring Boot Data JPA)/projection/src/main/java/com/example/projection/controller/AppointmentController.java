package com.example.projection.controller;

import com.example.projection.dto.AppointmentDto;
import com.example.projection.entity.requestDto.CreateAppointmentDto;
import com.example.projection.repository.AppointmentRepository;
import com.example.projection.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping(path = "/create")
    public AppointmentDto createAppointment(@RequestBody CreateAppointmentDto req){
        return appointmentService.createNewAppointment(req.getAppointment(),req.getPatientId(), req.getDoctorId());
    }


}
