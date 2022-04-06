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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/createUserDTO")
    public UserDTO createUser(@Validated @RequestBody UserDTO userDTO) {
        User user = Mapper.mapUserDTOToUser(userDTO);
        if (!userRepository.existsUserByLogin(user.getLogin())) {
            if (Objects.equals(userDTO.getRole(), "pupil")) {
                Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(userDTO.getName(), userDTO.getLastname(), userDTO.getPatronymic());
                if (pupil != null) {
                    pupil.setEmail(userDTO.getEmail());
                    userRepository.save(user);
                    User userForId = userRepository.findByLoginAndPasswordAndRole(user.getLogin(), user.getPassword(), user.getRole());
                    pupil.setUserId(userForId.getId());
                    pupilRepository.save(pupil);
                    return userDTO;
                }
            } else {
                Teacher teacher = teacherRepository.findByNameAndLastNameAndPatronymic(userDTO.getName(), userDTO.getLastname(), userDTO.getPatronymic());
                if (teacher != null) {
                    teacher.setEmail(userDTO.getEmail());
                    userRepository.save(user);
                    User userForId = userRepository.findByLoginAndPasswordAndRole(user.getLogin(), user.getPassword(), user.getRole());
                    teacher.setUserId(userForId.getId());
                    teacherRepository.save(teacher);
                    return userDTO;
                }
            }

            userDTO.setName("Такого ФИО не существует");
        } else {
            userDTO.setName("Такой пользователь уже существует. Введите другой логин");
        }
        return userDTO;
    }

    @DeleteMapping("/users/{login}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not present for the id :: " + employeeId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Validated @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setLogin(userDetails.getLogin());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());

        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
