package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PupilRepository extends JpaRepository<Pupil, Long> {
    Pupil findByNameAndLastnameAndPatronymic(String name, String lastname, String patronymic);
}
