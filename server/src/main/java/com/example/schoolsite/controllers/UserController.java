package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.UserDTO;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.exception.ResourceNotFoundException;
import com.example.schoolsite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId,
                                           @Validated @RequestBody UserDTO userDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(userService.updateUser(userId, userDetails));
    }
}
