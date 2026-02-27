package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

    Patient findByEmailAndPassword(String email, String password);

    // Better approach (recommended)
    Patient findByEmail(String email);
}