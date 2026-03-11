package com.practice.service;

import com.practice.model.Appointment;
import com.practice.model.Doctor;
import com.practice.model.Patient;
import com.practice.model.Status;
import com.practice.repository.AppointmentRepo;
import com.practice.repository.DoctorRepo;
import com.practice.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;


    @Override
    public String bookAppointment(int patientId, int doctorId, Appointment appointment) {
        Patient patient = patientRepo.findById(patientId).orElse(null);
        if (patient == null) {
            return "Patient not found.";
        }

        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);
        if (doctor == null) {
            return "Doctor not found.";
        }

        boolean slotTaken = appointmentRepo
                .existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                        doctorId, appointment.getAppointmentDate(), appointment.getAppointmentTime());
        if (slotTaken) {
            return "Slot already taken. Please choose another time.";
        }

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus(Status.PENDING);
        appointmentRepo.save(appointment);
        return "Appointment booked successfully.";
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId){
        return appointmentRepo.findByPatientId(patientId);
    }
    @Override
    public List<Appointment> getAppointmentsByDoctorId(int doctorId){
        return appointmentRepo.findByDoctorId(doctorId);
    }

}
