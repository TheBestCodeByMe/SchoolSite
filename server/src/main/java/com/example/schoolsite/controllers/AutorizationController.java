package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.entity.Role;
import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.enumiration.ERole;
import com.example.schoolsite.jwt.JwtUtils;
import com.example.schoolsite.pojo.JwtResponse;
import com.example.schoolsite.pojo.LoginRequest;
import com.example.schoolsite.pojo.MessageResponse;
import com.example.schoolsite.pojo.SignUpRequest;
import com.example.schoolsite.services.UserDetailsImpl;
import com.example.schoolsite.workWithDatabase.repo.PupilRepository;
import com.example.schoolsite.workWithDatabase.repo.RoleRepository;
import com.example.schoolsite.workWithDatabase.repo.TeacherRepository;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/auth")
public class AutorizationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRespository;

    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signIn")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()));

        // формируем jwt токен
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {

        if (userRespository.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic());
        Teacher teacher = teacherRepository.findByNameAndLastNameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic());

        if (signupRequest.getRole().equals("pupil")) {
            if (pupil == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Pupil is not exist"));
            }
        } else if (signupRequest.getRole().equals("teacher") || signupRequest.getRole().equals("Director")) {
            if (teacher == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Teacher is not exist"));
            }
        }

        User user = new User(signupRequest.getLogin(),
                signupRequest.getStatus(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> reqRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Your role is null"));
/*            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
              roles.add(userRole);*/
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "pupil" -> {
                        Role adminRole = roleRepository
                                .findByName(ERole.PUPIL)
                                .orElseThrow(() -> new RuntimeException("Error, Role Pupil is not found"));
                        roles.add(adminRole);
                    }
                    case "teacher" -> {
                        Role modRole = roleRepository
                                .findByName(ERole.TEACHER)
                                .orElseThrow(() -> new RuntimeException("Error, Role Teacher is not found"));
                        roles.add(modRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRespository.save(user);
        User userForId = userRespository.findByLogin(user.getLogin()).orElse(null);

        if (user.getRoles().equals(ERole.PUPIL)) {
            pupil.setUserId(userForId.getId());
            pupil.setEmail(signupRequest.getEmail());
            pupilRepository.save(pupil);
        } else {
            teacher.setUserId(userForId.getId());
            teacher.setEmail(signupRequest.getEmail());
            teacherRepository.save(teacher);
        }
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
