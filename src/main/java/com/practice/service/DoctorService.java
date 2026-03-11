package com.practice.service;

import com.practice.model.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor saveDoctor(Doctor doctor);

    String verify(String email, String password);
    List<Doctor> getAllDoctors();
}