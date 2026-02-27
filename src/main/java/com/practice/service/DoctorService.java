package com.practice.service;

import com.practice.model.Doctor;

public interface DoctorService {

    Doctor saveDoctor(Doctor doctor);

    Doctor login(String email, String password);
}