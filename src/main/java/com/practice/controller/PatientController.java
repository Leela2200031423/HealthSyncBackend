package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Patient;
import com.practice.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public Patient savePatient(@RequestBody Patient p) {
        return patientService.savePatient(p);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Working..";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return patientService.verify(email, password);
    }
}