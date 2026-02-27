package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Patient;
import com.practice.service.PatientService;

@RestController
@RequestMapping("/hel")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patientreg")
    public Patient savePatient(@RequestBody Patient p) {
        return patientService.savePatient(p);
    }


    @PostMapping("/login")
    public Patient login(@RequestBody Patient p) {
        return patientService.login(p.getEmail(), p.getPassword());
    }
}