package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin findByEmail(String email);

}