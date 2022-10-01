package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Calendar
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CalendarRepository : JpaRepository<Calendar?, Long?> {
    fun findByLessonNumberAndWeekDay(lessonNumber: Int, weekDay: Int): Calendar?
}