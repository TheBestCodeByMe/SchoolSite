package com.example.schoolsite.services.impl;

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
import com.example.schoolsite.services.AuthorizationService;
import com.example.schoolsite.workWithDatabase.repo.PupilRepository;
import com.example.schoolsite.workWithDatabase.repo.RoleRepository;
import com.example.schoolsite.workWithDatabase.repo.TeacherRepository;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PupilRepository pupilRepository;

    private final TeacherRepository teacherRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse authUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        // формируем jwt токен
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (userDetails.getId() != null) {
            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles);
        } else {
            return null;
        }
    }

    @Override
    public String registerUser(SignUpRequest signupRequest) {
        if (userRepository.existsByLogin(signupRequest.getLogin())) {
            return "Error: Username is exist";
        }

        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic());
        Teacher teacher = teacherRepository.findByNameAndLastNameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic());

        if (signupRequest.getRole().equals("pupil")) {
            if (pupil == null) {
                return "Error: Pupil is not exist";
            }
        } else if (signupRequest.getRole().equals("teacher") || signupRequest.getRole().equals("Director")) {
            if (teacher == null) {
                return "Error: Teacher is not exist";
            }
        }

        User user = new User(signupRequest.getLogin(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getStatus());

        Set<String> reqRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            return "Error: Your role is null";
/*            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
              roles.add(userRole);*/
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "pupil" -> {
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_PUPIL)
                                .orElseThrow(() -> new RuntimeException("Error, Role Pupil is not found"));
                        roles.add(adminRole);
                    }
                    case "teacher" -> {
                        Role modRole = roleRepository
                                .findByName(ERole.ROLE_TEACHER)
                                .orElseThrow(() -> new RuntimeException("Error, Role Teacher is not found"));
                        roles.add(modRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        User userForId = userRepository.findByLogin(user.getLogin()).orElse(null);

        Set<Role> role = new HashSet<>();
        role.add(new Role(ERole.ROLE_PUPIL));

        if (userForId.getRoles().equals(role)) {
            pupil.setUserId(userRepository.getById(userForId.getId()));
            pupil.setEmail(signupRequest.getEmail());
            pupilRepository.save(pupil);
        } else {
            teacher.setUserId(userRepository.getById(userForId.getId()));
            teacher.setEmail(signupRequest.getEmail());
            teacherRepository.save(teacher);
        }
        return "User CREATED";
    }
}