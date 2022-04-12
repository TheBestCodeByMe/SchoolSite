package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.DiaryDTO;
import com.example.schoolsite.entity.AcademicPerfomance;
import com.example.schoolsite.entity.Teacher;
import com.example.schoolsite.workWithDatabase.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/diary")
public class DiaryController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private AcademicPerfomanceRepository academicPerfomanceRepository;

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private SheduleRepository sheduleRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @PostMapping("/addAttendanceAndAcademicPerfomance")
    public DiaryDTO addAttendanceAndAcademicPerfomance(@RequestBody DiaryDTO diaryDTO) {
        return diaryDTO;
    }
}
