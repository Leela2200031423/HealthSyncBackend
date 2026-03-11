package com.practice.controller;

import com.practice.model.Appointment;
import com.practice.model.Doctor;
import com.practice.repository.AppointmentRepo;
import com.practice.service.AppointmentService;
import com.practice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Patient;
import com.practice.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/register")
    public Patient savePatient(@RequestBody Patient p) {
        return patientService.savePatient(p);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Working..";
    }
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return patientService.verify(email, password);
    }


//    @PostMapping("/book-appointment")
//    public ResponseEntity<String> bookAppointment(@RequestParam int patientId, @RequestParam int doctorId, @RequestBody Appointment appointment) {
//        String result = String.valueOf(appointmentService.bookAppointment(patientId, doctorId, appointment));
//        if (result.equals("Appointment booked successfully.")) {
//            return ResponseEntity.ok(result);
//        } else {
//            return ResponseEntity.badRequest().body(result);
//        }
//    }

    @PostMapping("/book-appointment")
    public ResponseEntity<String> bookAppointment(@RequestParam int doctorId, @RequestBody Appointment appointment, Authentication authentication) {
        String username = authentication.getName();
        Patient patient = patientService.getPatientByUsername(username);

        if(patient== null){
            return ResponseEntity.badRequest().body("Patient not Found for logged-in user");
        }

        String result = String.valueOf(appointmentService.bookAppointment(patient.getId(),doctorId,appointment));
        if (result.contains("successfully")) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

}