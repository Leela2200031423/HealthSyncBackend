package com.practice.service;

import com.practice.model.Appointment;
import com.practice.model.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient p);

    String verify(String email, String password);

    Patient getPatientByUsername(String username);
    Patient getProfile(int patientId);

    Patient updateProfile(int patientId,Patient patient);
    //List<Appointment> getAllAppointments();
}