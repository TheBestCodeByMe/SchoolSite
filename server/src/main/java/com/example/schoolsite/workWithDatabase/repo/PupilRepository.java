package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Long> {
    Pupil findByNameAndLastnameAndPatronymic(String name, String lastname, String patronymic);
    Pupil findByUserId(Long userId);
    List<Pupil> findAllByClassroomId(Long classroomId);
}
