package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Pupil
import com.example.schoolsite.entity.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository : JpaRepository<Teacher?, Long?> {
    fun findByNameAndLastNameAndPatronymic(name: String?, lastname: String?, patronymic: String?): Teacher?
    fun findByUserId(userId: Long?): Teacher?
}