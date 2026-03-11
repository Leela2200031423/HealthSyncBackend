package com.practice.service;

import com.practice.model.Patient;

public interface PatientService {

    Patient savePatient(Patient p);

    String verify(String email, String password);

    Patient getPatientByUsername(String username);
}