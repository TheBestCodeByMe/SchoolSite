package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.UserDTO;
import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.exception.ResourceNotFoundException;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.workWithDatabase.repo.PupilRepository;
import com.example.schoolsite.workWithDatabase.repo.TeacherRepository;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PupilRepository pupilRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId)));
        Pupil pupil = pupilRepository.findByUserId(userId);
        Teacher teacher = teacherRepository.findByUserId(userId);
        UserDTO userDTO;
        if(pupil != null){
            userDTO = Mapper.mapUserToUserDTO(user.get(), pupil);
        } else {
            userDTO = Mapper.mapUserTeacherToUserDTO(user.get(), teacher);
        }

        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId,
                                           @Validated @RequestBody UserDTO userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        Pupil pupil = pupilRepository.findByUserId(userId);
        Teacher teacher = teacherRepository.findByUserId(userId);

        if(pupil != null){
            pupil.setEmail(userDetails.getEmail());
            pupil.setName(userDetails.getName());
            pupil.setLastname(userDetails.getLastname());
            pupil.setPatronymic(userDetails.getPatronymic());
            pupilRepository.save(pupil);
        } else {
            teacher.setEmail(userDetails.getEmail());
            teacher.setName(userDetails.getName());
            teacher.setLastName(userDetails.getLastname());
            teacher.setPatronymic(userDetails.getPatronymic());
            teacherRepository.save(teacher);
        }

        user.setLogin(userDetails.getLogin());

        userRepository.save(user);
        return ResponseEntity.ok(userDetails);
    }
}
