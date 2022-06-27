package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByNameAndLastNameAndPatronymic(String name, String lastname, String patronymic);
    Teacher findByUserId(Long userId);
}