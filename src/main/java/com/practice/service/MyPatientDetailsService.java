package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.model.Patient;
import com.practice.model.PatientPrincipal;
import com.practice.repository.PatientRepo;

@Service
public class MyPatientDetailsService implements UserDetailsService{
	
	@Autowired
	private PatientRepo patientRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Patient patient = patientRepo.findByEmail(email);
		if(patient == null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException(email + "Patient Not Found");
		}
		return new PatientPrincipal(patient);
	}

}
