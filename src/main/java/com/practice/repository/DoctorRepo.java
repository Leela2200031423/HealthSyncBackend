package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.model.Doctor;
import org.springframework.stereotype.Repository;

@Repository

public interface DoctorRepo extends JpaRepository<Doctor, Integer> {

    Doctor findByEmail(String email);

}