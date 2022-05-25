package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.Question;
import com.example.schoolsite.services.AskQuestionService;
import com.example.schoolsite.workWithDatabase.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/askQuestion")
@RequiredArgsConstructor
public class AskQuestionController {

    private final AskQuestionService askQuestionService;

    @PostMapping("/addQuestion")
    public Question createQuestion(@Validated @RequestBody Question question) {
        return askQuestionService.createQuestion(question);
    }
}
