package com.example.schoolsite.services.impl;

import com.example.schoolsite.entity.Question;
import com.example.schoolsite.services.AskQuestionService;
import com.example.schoolsite.workWithDatabase.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskQuestionServiceImpl implements AskQuestionService {

    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        System.out.println(question);
        Question questionFromRepo = questionRepository.findByQuestion(question.getQuestion());
        if (questionFromRepo == null) {
            questionRepository.save(question);
        }
        return question;
    }
}