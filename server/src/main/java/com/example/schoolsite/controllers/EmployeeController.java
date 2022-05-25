package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getAll")
    public List<Teacher> getAllTeacher() {
        return employeeService.getAllTeacher();
    }

    @PostMapping("/getByFIO")
    public List<Teacher> getTeacherByFIO(@Validated @RequestBody Teacher teacher) {
        return employeeService.getTeacherByFIO(teacher);
    }

    @PostMapping("/getByUserId")
    public Teacher getTeacherByUserId(@RequestBody String userId) {
        return employeeService.getTeacherByUserId(userId);
    }
}
