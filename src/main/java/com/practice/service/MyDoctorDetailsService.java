package com.practice.service;

import com.practice.model.Doctor;
import com.practice.model.DoctorPrincipal;
import com.practice.model.Patient;
import com.practice.model.PatientPrincipal;
import com.practice.repository.DoctorRepo;
import com.practice.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyDoctorDetailsService implements UserDetailsService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Doctor doctor = doctorRepo.findByEmail(email);
        if (doctor == null) {
            System.out.println("Doctor Not Found");
            throw new UsernameNotFoundException(email + "Doctor Not Found");
        }
        return new DoctorPrincipal(doctor);
    }
}

