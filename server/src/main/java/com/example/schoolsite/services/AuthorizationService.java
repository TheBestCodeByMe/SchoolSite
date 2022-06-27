package com.example.schoolsite.services;

import com.example.schoolsite.pojo.JwtResponse;
import com.example.schoolsite.pojo.LoginRequest;
import com.example.schoolsite.pojo.SignUpRequest;

public interface AuthorizationService {

    JwtResponse authUser(LoginRequest loginRequest);

    String registerUser(SignUpRequest signupRequest);
}
