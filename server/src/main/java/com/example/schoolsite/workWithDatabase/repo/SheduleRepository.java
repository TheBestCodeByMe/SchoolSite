package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Shedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheduleRepository extends JpaRepository<Shedule, Long> {
}
