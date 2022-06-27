package com.example.schoolsite.services.impl;


import com.example.schoolsite.dto.SheduleDTO;
import com.example.schoolsite.entity.*;
import com.example.schoolsite.exception.ResourceNotFoundException;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.services.SheduleService;
import com.example.schoolsite.workWithDatabase.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheduleServiceImpl implements SheduleService {

    private final AttendanceRepository attendanceRepository;

    private final AcademicPerfomanceRepository academicPerfomanceRepository;

    private final PupilRepository pupilRepository;

    private final SheduleRepository sheduleRepository;

    private final SubjectRepository subjectRepository;

    private final ClassroomRepository classroomRepository;

    private final TeacherRepository teacherRepository;

    private final CalendarRepository calendarRepository;

    @Override
    public List<SheduleDTO> getScheduleByIdAndDate(@PathVariable(value = "userId") Long userId, @PathVariable(value = "date") String date) {
        return getScheduleDTOByIdAndDate(userId, date);
    }

    @Override
    public List<SheduleDTO> getScheduleDTOByIdAndDate(Long userId, String date) {
        Pupil pupil = pupilRepository.findByUserId(userId);
        List<Shedule> sheduleList = sheduleRepository.findAllByClassroomIDAndDate(pupil.getClassroomId().getId(), Date.valueOf(date));
        List<SheduleDTO> sheduleDTOList = new ArrayList<>();

        Classroom classroom = classroomRepository.getById(pupil.getClassroomId().getId());

        for (Shedule shedule : sheduleList) {
            Subject subject = subjectRepository.getById(shedule.getSubjectID().getId());
            Teacher teacher = teacherRepository.getById(shedule.getTeacherID().getId());
            Calendar calendar = calendarRepository.getById(shedule.getCalendarId().getId());
            SheduleDTO sheduleDTO = Mapper.mapSheduleToSheduleDTO(shedule, calendar, subject, teacher, classroom);
            sheduleDTOList.add(sheduleDTO);
        }
        return sheduleDTOList;
    }

}