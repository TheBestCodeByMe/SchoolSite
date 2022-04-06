package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Shedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface SheduleRepository extends JpaRepository<Shedule, Long> {
    Shedule findByCalendarIdAndClassroomIDAndDateAndSubjectIDAndTeacherIDAndWeekDay(Long calendarId, Long classroomID, Date date, Long subjectID, Long teacherID, int weekDay);
    Shedule findByTeacherIDAndCalendarIdAndDate(Long teacherId, Long calendarId, Date date);
    Shedule findByCalendarIdAndClassroomIDAndDate(Long calendarId, Long classroomId, Date date);
}
