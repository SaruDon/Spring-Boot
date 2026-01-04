package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("employees")
@RestController
public class EmployeeController {

//    @GetMapping()
//    public String getMySuperSecretMessage(){
//        return "My Message";
//    }

    @GetMapping(path = "{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(
                employeeId,                         // id (Long)
                "Sarvesh",                  // name (String)
                "sarvesh@gmail.com",        // email (String)
                25,                         // age (Integer)
                LocalDate.now(),            // dateOfJoining (LocalDate)
                true                        // isActive (Boolean)
        );
    }

    @GetMapping()
    public String getAllEmployees(@RequestParam(required = false) Long age){
        return "age: "+age;
    }


    @PostMapping
    public String createNewEmployee(){
        return "Hello from employee";
    }
}

