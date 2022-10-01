package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Shedule
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Date
import java.util.List
import org.springframework.stereotype.Repository

@Repository
interface SheduleRepository : JpaRepository<Shedule?, Long?> {
    fun findByCalendarIdAndClassroomIDAndDateAndSubjectIDAndTeacherIDAndWeekDay(calendarId: Long?, classroomID: Long?, date: Date?, subjectID: Long?, teacherID: Long?, weekDay: Int): Shedule?
    fun findByTeacherIDAndCalendarIdAndDate(teacherId: Long?, calendarId: Long?, date: Date?): Shedule?
    fun findByCalendarIdAndClassroomIDAndDate(calendarId: Long?, classroomId: Long?, date: Date?): Shedule?
    fun findByDateAndClassroomIDAndSubjectID(date: Date?, classroomId: Long?, subjectId: Long?): Shedule?
    fun findAllByClassroomID(classroomId: Long?): List<Shedule?>?
    fun findAllByClassroomIDAndDate(classroomId: Long?, date: Date?): List<Shedule?>?
}