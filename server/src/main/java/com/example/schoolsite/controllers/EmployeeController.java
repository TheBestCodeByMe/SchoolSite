package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.workWithDatabase.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/getAll")
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @GetMapping("/getByFIO")
    public Teacher getTeacherByFIO(@Validated @RequestBody Teacher teacher) {
        return teacherRepository.findByNameAndLastNameAndPatronymic(teacher.getName(), teacher.getLastName(), teacher.getPatronymic());
    }

    @PostMapping("/getByUserId")
    public Teacher getTeacherByFIO(@RequestBody String userId) {
        return teacherRepository.findByUserId(Long.parseLong(userId));
    }
}
