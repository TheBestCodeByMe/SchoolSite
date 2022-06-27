package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilID(Long classId, Long lessonId, Long pupilId);
    List<Attendance> findAllByPupilID(Long pupilId);
}