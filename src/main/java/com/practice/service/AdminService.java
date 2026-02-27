package com.practice.service;

import com.practice.model.Admin;

public interface AdminService {

    Admin login(String email, String password);

}