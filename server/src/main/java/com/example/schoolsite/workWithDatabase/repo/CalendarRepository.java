package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Calendar findByLessonNumberAndWeekDay(int lessonNumber, int weekDay);
}
