package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository : JpaRepository<Subject?, Long?> {
    fun findBySubjectName(subjectName: String?): Subject?
}