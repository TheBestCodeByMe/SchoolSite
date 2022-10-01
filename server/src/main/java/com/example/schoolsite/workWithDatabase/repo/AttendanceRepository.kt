package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Attendance
import org.springframework.data.jpa.repository.JpaRepository
import java.util.List
import org.springframework.stereotype.Repository

@Repository
interface AttendanceRepository : JpaRepository<Attendance?, Long?> {
    fun existsByClassIDAndLessonIDAndPupilID(classId: Long?, lessonId: Long?, pupilId: Long?): Boolean
    fun findAllByPupilID(pupilId: Long?): List<Attendance?>?
}