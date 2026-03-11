package com.practice.repository;

import com.practice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

    List<Appointment> findByPatientId(int patientId);

    List<Appointment> findByDoctorId(int doctorId);

    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(int doctorId, LocalDate appointmentDate, LocalTime appointmentTime);
}
