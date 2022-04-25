package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.Role;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.enumiration.ERole;
import com.example.schoolsite.pojo.JwtResponse;
import com.example.schoolsite.pojo.LoginRequest;
import com.example.schoolsite.pojo.MessageResponse;
import com.example.schoolsite.pojo.SignUpRequest;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class AutorizationControllerTest {

    User expectedUser;
    AutorizationController autorizationController;
    Set<Role> role = new HashSet<>();
    List<String> role1;
    LoginRequest loginRequest;
    JwtResponse jwtResponse;
    SignUpRequest signUpRequest;

    @BeforeEach
    void init() {
        expectedUser = new User();
        autorizationController = new AutorizationController();
        expectedUser.setLogin("pupilLogin");
        expectedUser.setPassword("andreyLogin");
        expectedUser.setStatus("unBlock");
        role.add(new Role(ERole.ROLE_PUPIL));
        //expectedUser.setRoles(role);

        //role1.add("ROLE_PUPIL");

        loginRequest = new LoginRequest();
        loginRequest.setUsername("pupilLogin");
        loginRequest.setPassword("andreyLogin");

        jwtResponse = new JwtResponse();
        jwtResponse.setLogin("pupilLogin");
        //jwtResponse.setRoles(role1);
        //expectedUser.setRole("ADMIN");

        signUpRequest = new SignUpRequest();
        signUpRequest.setLogin("1");
        signUpRequest.setEmail("1");
        signUpRequest.setPassword("1");
        signUpRequest.setStatus("UnBlock");
        signUpRequest.setLink("");
        signUpRequest.setName("1");
        signUpRequest.setLastname("1");
        signUpRequest.setPatronymic("1");
        Set<String> rol = new HashSet<>();
        rol.add("pupil");
        signUpRequest.setRole(rol);
    }

    @DisplayName("Testing search user which exists in database")
    @Tag("USERS-TEST")
    @ParameterizedTest
    @ValueSource(ints = {1})
    void authUser() {
       Assertions.assertNotEquals("Your login or password are invalid", autorizationController.authUser(loginRequest));
    }

    @Test
    @DisplayName("Testing search user which non exists in database")
    @Tag("USERS-TEST")
    void testFindUserByIdWhichNonExists() {
        ResponseEntity<?> responseEntity = autorizationController.authUser(loginRequest);
        Assertions.assertEquals("Your login or password are invalid", responseEntity);
    }


    @Test
    @DisplayName("Testing saving user success")
    void registerUser() {
        Assertions.assertEquals(ResponseEntity.ok(new MessageResponse("User CREATED")), autorizationController.registerUser(signUpRequest));
    }

    @Test
    @DisplayName("Testing saving user failed")
    void testSaveUserFailed() {
        Assertions.assertEquals(new MessageResponse("Error: Username is exist"),autorizationController.registerUser(signUpRequest));
    }
}