package com.example.schoolsite.workWithDatabase.repo

import com.example.schoolsite.entity.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question?, Long?> {
    fun findByQuestion(question: String?): Question?
}