package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.DiaryDTO;
import com.example.schoolsite.dto.SheduleDTO;
import com.example.schoolsite.entity.*;
import com.example.schoolsite.exception.ResourceNotFoundException;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.services.DiaryServiceImpl;
import com.example.schoolsite.workWithDatabase.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class SchedulController {

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

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @GetMapping("/getScheduleDTOPupil/{userId}/{date}")
    public ResponseEntity<List<SheduleDTO>> getScheduleByIdAndDate(@PathVariable(value = "userId") Long userId, @PathVariable(value = "date") String date)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(getScheduleDTOByIdAndDate(userId, date));
    }

    public List<SheduleDTO> getScheduleDTOByIdAndDate(Long userId, String date) {
        Pupil pupil = pupilRepository.findByUserId(userId);
        List<Shedule> sheduleList = sheduleRepository.findAllByClassroomIDAndDate(pupil.getClassroomId(), Date.valueOf(date));
        List<SheduleDTO> sheduleDTOList = new ArrayList<>();

        Classroom classroom = classroomRepository.getById(pupil.getClassroomId());

        for (Shedule shedule : sheduleList) {
            Subject subject = subjectRepository.getById(shedule.getSubjectID());
            Teacher teacher = teacherRepository.getById(shedule.getTeacherID());
            Calendar calendar = calendarRepository.getById(shedule.getCalendarId());

            SheduleDTO sheduleDTO = Mapper.mapSheduleToSheduleDTO(shedule, calendar, subject, teacher, classroom);
            sheduleDTOList.add(sheduleDTO);
        }
        return sheduleDTOList;
    }
}
