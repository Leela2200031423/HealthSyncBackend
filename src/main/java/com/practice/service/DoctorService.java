package com.practice.service;

import com.practice.model.Doctor;

public interface DoctorService {

    Doctor saveDoctor(Doctor doctor);

    String verify(String email, String password);
}