package com.example.schoolsite.controllers;

import com.example.schoolsite.dto.PupilDTO;
import com.example.schoolsite.entity.Classroom;
import com.example.schoolsite.entity.Parents;
import com.example.schoolsite.entity.Pupil;
import com.example.schoolsite.entity.Question;
import com.example.schoolsite.map.Mapper;
import com.example.schoolsite.workWithDatabase.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/askQuestion")
public class AskQuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/addQuestion")
    public Question createQuestion(@Validated @RequestBody Question question) {
        Question questionFromRepo = questionRepository.findByQuestion(question.getQuestion());
        if (questionFromRepo == null) {
            questionRepository.save(question);
        }
        return question;
    }
}
