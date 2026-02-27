package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Doctor;
import com.practice.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // ✅ Doctor Login API
    @PostMapping("/login")
    public Doctor login(@RequestBody Doctor doctor) {
        return doctorService.login(doctor.getEmail(), doctor.getPassword());
    }
}