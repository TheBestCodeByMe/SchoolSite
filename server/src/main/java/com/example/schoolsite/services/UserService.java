package com.example.schoolsite.services;

import com.example.schoolsite.dto.UserDTO;
import com.example.schoolsite.entity.User;
import com.example.schoolsite.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

   UserDTO getUserById(Long userId)
            throws ResourceNotFoundException;

    UserDTO updateUser(Long userId,
                           UserDTO userDetails) throws ResourceNotFoundException;
}
