package com.practice.service;

import com.practice.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.model.Patient;
import com.practice.repository.PatientRepo;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;
    
    @Autowired
    private JWTService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Patient savePatient(Patient p) {
    	p.setPassword(passwordEncoder.encode(p.getPassword()));
        return patientRepo.save(p);
    }

//    @Override
//    public String verify(String email, String password) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//
//        if(authentication.isAuthenticated()){
//            return jwtService.generateToken(email);
//        }
//        return "FAiled";
//    }
@Override
public String verify(String email, String password) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

    if(authentication.isAuthenticated()){
        Patient patient = patientRepo.findByEmail(email);
        return jwtService.generateToken(email,"ROLE_PATIENT",patient.getId());
    }
    return "FAiled";
}

    @Override
    public Patient getPatientByUsername(String username) {
        return patientRepo.findByEmail(username);
    }

    @Override
    public Patient getProfile(int patientId) {
        Patient patient = patientRepo.findById(patientId).orElseThrow();
        return patient;
    }

    @Override
    public Patient updateProfile(int patientId,Patient patient) {
        Patient existingPatient = patientRepo.findById(patientId).orElseThrow();
        existingPatient.setName(patient.getName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setGender(patient.getGender());
        existingPatient.setPhno(patient.getPhno());
        return patientRepo.save(existingPatient);
    }

//    @Override
//    public List<Appointment> getAllAppointments() {
//        return
//    }
}