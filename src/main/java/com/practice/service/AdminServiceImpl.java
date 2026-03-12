package com.practice.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.model.Admin;
import com.practice.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

//    @Override
//    public String verify(String email, String password) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
//
//        if(authentication.isAuthenticated()){
//            return  jwtService.generateToken(email);
//        }
//        return "FAiled";
//
//    }


    public String verify(String email, String password) {
        logger.info("Attempting to authenticate admin with email: {}", email);
        Admin admin = adminRepo.findByEmail(email);
        if (admin == null) {
            logger.error("Admin not found in database for email: {}", email);
            throw new UsernameNotFoundException("Admin Not Found");
        }

        logger.info("Admin found: {}", admin.getEmail());
        logger.debug("Stored password hash: {}", admin.getPassword());
        logger.debug("Input password: {}", password);

        // Test password match
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        boolean passwordMatches = encoder.matches(password, admin.getPassword());
        logger.info("Password matches: {}", passwordMatches);

        if (!passwordMatches) {
            logger.error("Password does not match for admin: {}", email);
            throw new RuntimeException("Invalid credentials");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            logger.info("Authentication successful: {}", authentication.isAuthenticated());

            if (authentication.isAuthenticated()) {
                Admin admin1  = adminRepo.findByEmail(email);
                String token = jwtService.generateToken(email,"ROLE_ADMIN",admin1.getId());
                logger.info("JWT token generated successfully");
                return token;
            }
        } catch (UsernameNotFoundException e) {
            logger.error("UsernameNotFoundException: {}", e.getMessage());
            throw new UsernameNotFoundException("Admin Not Found");
        } catch (Exception e) {
            logger.error("Authentication exception: {}", e.getMessage(), e);
            throw new RuntimeException("Invalid credentials: " + e.getMessage());
        }

        throw new RuntimeException("Authentication failed");
    }
    }
