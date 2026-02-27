package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.model.Patient;
import com.practice.repository.PatientRepo;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public Patient savePatient(Patient p) {
        return patientRepo.save(p);
    }

    @Override
    public Patient login(String email, String password) {
        return patientRepo.findByEmailAndPassword(email, password);
    }
}