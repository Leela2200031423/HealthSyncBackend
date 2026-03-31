package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.model.Doctor;
import com.practice.repository.DoctorRepo;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Doctor saveDoctor(Doctor d) {
        if (d == null) {
            throw new IllegalArgumentException("Doctor payload is required");
        }
        if (isBlank(d.getName()) || isBlank(d.getSpecialized()) || isBlank(d.getEmail())
                || isBlank(d.getPhno()) || isBlank(d.getGender()) || d.getAge() <=0) {
            throw new IllegalArgumentException("Invalid doctor details");
        }
        if (isBlank(d.getPassword())) {
            throw new IllegalArgumentException("Password is required");
        }

        d.setPassword(passwordEncoder.encode(d.getPassword().trim()));
        return doctorRepo.save(d);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

//
//    @Override
//    public Doctor saveDoctor(Doctor doctor) {
//        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
//        return doctorRepo.save(doctor);
//    }

    @Override
    public String verify(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if(authentication.isAuthenticated()){
            Doctor doctor = doctorRepo.findByEmail(email);
            return jwtService.generateToken(email,"ROLE_DOCTOR",doctor.getId());
        }
        return "FAiled";
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor getProfile(int doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow();
        return doctor;
    }
}