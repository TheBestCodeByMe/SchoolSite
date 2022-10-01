package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Pupil
import org.springframework.data.jpa.repository.JpaRepository
import java.util.List
import org.springframework.stereotype.Repository

@Repository
interface PupilRepository : JpaRepository<Pupil?, Long?> {
    fun findByNameAndLastnameAndPatronymic(name: String?, lastname: String?, patronymic: String?): Pupil?
    fun findByUserId(userId: Long?): Pupil?
    fun findAllByClassroomId(classroomId: Long?): List<Pupil?>?
}