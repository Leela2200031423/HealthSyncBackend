package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.model.Admin;
import com.practice.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public Admin login(String email, String password) {

        Admin admin = adminRepo.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }

        return null;
    }
}