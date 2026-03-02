package com.practice.service;

import com.practice.model.*;
import com.practice.repository.AdminRepo;
import com.practice.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.repository.PatientRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private DoctorRepo doctorRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Admin admin = adminRepo.findByEmail(email);
		if(admin != null){
			System.out.println("Admin Found");
			return new AdminPrincipal(admin);
		}

		Doctor doctor = doctorRepo.findByEmail(email);
		if(doctor != null){
			System.out.println("Doctor Found");
			return new DoctorPrincipal(doctor);
		}


		Patient patient = patientRepo.findByEmail(email);
		if(patient != null) {
			System.out.println("User Found");
			throw new UsernameNotFoundException(email + "Patient Not Found");
		}
		return new PatientPrincipal(patient);
	}

}
