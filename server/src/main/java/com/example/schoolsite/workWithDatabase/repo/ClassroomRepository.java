package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
