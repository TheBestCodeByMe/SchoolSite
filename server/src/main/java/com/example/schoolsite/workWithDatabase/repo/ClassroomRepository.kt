package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Classroom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassroomRepository : JpaRepository<Classroom?, Long?> {
    fun findClassroomByName(name: String?): Classroom?
}