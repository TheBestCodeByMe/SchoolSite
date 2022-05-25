package com.example.schoolsite.services;

import com.example.schoolsite.dto.UserDTO;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    ResponseEntity<UserDTO> getUserById(Long userId)
            throws ResourceNotFoundException;

    ResponseEntity<UserDTO> updateUser(Long userId,
                                       UserDTO userDetails) throws ResourceNotFoundException;
}
