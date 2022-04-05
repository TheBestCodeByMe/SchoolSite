package com.example.schoolsite.workWithDatabase.repo;

import com.example.schoolsite.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findBySubjectName(String subjectName);
}
