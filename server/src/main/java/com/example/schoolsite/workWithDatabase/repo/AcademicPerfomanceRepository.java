package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.AcademicPerfomance;
import com.example.schoolsite.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicPerfomanceRepository extends JpaRepository<AcademicPerfomance, Long> {
}
