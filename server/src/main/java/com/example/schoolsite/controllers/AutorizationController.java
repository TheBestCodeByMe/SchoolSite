package com.example.schoolsite.controllers;

import com.example.schoolsite.pojo.JwtResponse;
import com.example.schoolsite.pojo.LoginRequest;
import com.example.schoolsite.pojo.MessageResponse;
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
        JwtResponse response = authorizationService.authUser(loginRequest);
        if(response!=null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Your login or password are invalid"));
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {
        String status = authorizationService.registerUser(signupRequest);
        if(status.contains("Error")){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(status));
        } else {
            return ResponseEntity.ok(new MessageResponse(status));
        }
    }
}
