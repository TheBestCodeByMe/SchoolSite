package com.example.schoolsite.controllers

import com.example.schoolsite.entity.Question

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/askQuestion")
@RequiredArgsConstructor
class AskQuestionController {
    private val askQuestionService: AskQuestionService? = null
    @PostMapping("/addQuestion")
    fun createQuestion(@Validated @RequestBody question: Question?): Question {
        return askQuestionService.createQuestion(question)
    }
}