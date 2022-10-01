package com.example.schoolsite.services

import com.example.schoolsite.entity.Question

interface AskQuestionService {
    fun createQuestion(@Validated @RequestBody question: Question?): Question?
}