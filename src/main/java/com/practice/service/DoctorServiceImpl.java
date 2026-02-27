package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.model.Doctor;
import com.practice.repository.DoctorRepo;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public Doctor login(String email, String password) {

        Doctor doctor = doctorRepo.findByEmail(email);

        if (doctor != null && doctor.getPassword().equals(password)) {
            return doctor;
        }

        return null;
    }
}