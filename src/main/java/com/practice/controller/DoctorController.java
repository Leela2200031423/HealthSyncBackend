package com.practice.controller;

import com.practice.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Doctor;
import com.practice.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JWTService jwtService;

    // ✅ Doctor Login API
    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password) {
        return doctorService.verify(email,password);
    }

    @GetMapping("/profile")
    public ResponseEntity<Doctor> getProfile(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        int doctorId = jwtService.extractId(token);
        return ResponseEntity.ok(doctorService.getProfile(doctorId));
    }
}