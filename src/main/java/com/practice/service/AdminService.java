package com.practice.service;

import com.practice.model.Admin;
import com.practice.model.Doctor;

import java.util.List;

public interface AdminService {

    String verify(String email, String password);

    List<Doctor> getAllDoctors();
}