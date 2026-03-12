package com.practice.controller;

import com.practice.model.Appointment;
import com.practice.model.Doctor;
import com.practice.repository.AppointmentRepo;
import com.practice.service.AppointmentService;
import com.practice.service.DoctorService;
import com.practice.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private JWTService jwtService;

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

    @GetMapping("/profile")
    public ResponseEntity<Patient> getProfile(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        int patientId = jwtService.extractId(token);
        return ResponseEntity.ok(patientService.getProfile(patientId));
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
//
//    @PutMapping("/updateprofile")
//    public ResponseEntity<Patient> updateProfile(@RequestBody Patient patient, @RequestHeader("Authorization") String authHeader){
//       String token = authHeader.substring(7);
//       int patientId = jwtService.extractId(token);
//       Patient updatedPatient = patientService.updateProfile(patientId,patient);
//         return ResponseEntity.ok(updatedPatient);
//    }

    @PutMapping("/updateprofile")
    public ResponseEntity<?> updateProfile(@RequestBody Patient patient, @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);
            String email = jwtService.extractUserName(token);
            Patient existingPatient = patientService.getPatientByUsername(email);
            if (existingPatient == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
            }
            existingPatient.setName(patient.getName());
            existingPatient.setAge(patient.getAge());
            existingPatient.setGender(patient.getGender());
            existingPatient.setPhno(patient.getPhno());
            Patient updated = patientService.savePatient(existingPatient);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    //@GetMapping("/getappointments")


}