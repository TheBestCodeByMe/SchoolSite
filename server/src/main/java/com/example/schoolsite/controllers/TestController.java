package com.example.schoolsite.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/test")
public class TestController {
    // Может любой стучаться
    @GetMapping("/all")
    public String allAccess() {
        return "public API";
    }

    // Любой из перечисленных
    @GetMapping("/user")
    @PreAuthorize("hasRole('TEACHER') or hasRole('PUPIL') or hasRole('DIRECTOR')")
    public String userAccess() {
        return "user API";
    }

    // Только ученики
    @GetMapping("/pupil")
    @PreAuthorize("hasRole('PUPIL')")
    public String pupilAccess() {
        return "user API";
    }

    // Только учителя могут
    @GetMapping("/teacher")
    @PreAuthorize("hasRole('TEACHER')")
    public String teacherAccess() {
        return "moderator API";
    }

    // Только директор может
    @GetMapping("/director")
    @PreAuthorize("hasRole('DIRECTOR')")
    public String directorAccess() {
        return "admin API";
    }
}
