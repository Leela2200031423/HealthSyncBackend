package com.practice.service;

import com.practice.model.Admin;
import com.practice.model.AdminPrincipal;
import com.practice.model.Doctor;
import com.practice.model.DoctorPrincipal;
import com.practice.repository.AdminRepo;
import com.practice.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Admin admin = adminRepo.findByEmail(email);
        if (admin == null) {
            System.out.println("Admin Not Found");
            throw new UsernameNotFoundException(email + "Admin Not Found");
        }
        return new AdminPrincipal(admin);
    }
}

