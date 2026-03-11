package com.practice.service;

import com.practice.model.Appointment;

import java.util.List;

public interface AppointmentService {

    String bookAppointment(int patientId, int doctorId, Appointment appointment);
    List<Appointment> getAppointmentsByPatientId(int patientId);
    List<Appointment> getAppointmentsByDoctorId(int doctorId);

}
