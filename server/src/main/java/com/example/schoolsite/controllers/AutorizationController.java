package com.example.schoolsite.controllers;

import com.example.schoolsite.pojo.LoginRequest;
import com.example.schoolsite.pojo.SignUpRequest;
import com.example.schoolsite.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/signIn")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
       return authorizationService.authUser(loginRequest);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {
          return authorizationService.registerUser(signupRequest);
    }
}
