package com.practice.service;

import com.practice.model.Patient;

public interface PatientService {

    Patient savePatient(Patient p);

    Patient login(String email, String password);
}