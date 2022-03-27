package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
