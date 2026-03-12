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


    @Override
    public Doctor saveDoctor(Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return doctorRepo.save(doctor);
    }

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
}