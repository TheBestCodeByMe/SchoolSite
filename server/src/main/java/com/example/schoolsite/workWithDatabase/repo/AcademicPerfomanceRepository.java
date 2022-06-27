package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.AcademicPerfomance;
import com.example.schoolsite.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicPerfomanceRepository extends JpaRepository<AcademicPerfomance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilID(Long classId, Long lessonId, Long pupilId);
    List<AcademicPerfomance> findAllByPupilID(Long pupilId);
}