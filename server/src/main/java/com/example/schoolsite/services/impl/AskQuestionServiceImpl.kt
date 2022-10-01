package com.example.schoolsite.services.impl

import com.example.schoolsite.entity.Question

@Service
@RequiredArgsConstructor
class AskQuestionServiceImpl : AskQuestionService {
    private val questionRepository: QuestionRepository? = null
    fun createQuestion(question: Question): Question {
        val questionFromRepo: Question = questionRepository.findByQuestion(question.getQuestion())
        if (questionFromRepo == null) {
            questionRepository.save(question)
        }
        return question
    }
}