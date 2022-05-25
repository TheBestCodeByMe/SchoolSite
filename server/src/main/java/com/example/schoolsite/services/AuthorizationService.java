package com.example.schoolsite.services;

import com.example.schoolsite.pojo.LoginRequest;
import com.example.schoolsite.pojo.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthorizationService {

    ResponseEntity<?> authUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignUpRequest signupRequest);
}
