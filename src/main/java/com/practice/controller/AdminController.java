package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice.model.Admin;
import com.practice.model.Doctor;
import com.practice.service.AdminService;
import com.practice.service.DoctorService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private DoctorService doctorService;

    // ✅ Admin Login API
    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password) {
        return adminService.verify(email, password);
    }
    


    @PostMapping("/adddoctor")
    public Doctor addDoctor(@RequestBody Doctor d) {

    	return doctorService.saveDoctor(d);
    }
}