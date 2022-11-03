package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findClassroomByName(String name);
}
