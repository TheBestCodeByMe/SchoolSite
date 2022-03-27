package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
