package com.example.schoolsite.services;

import com.example.schoolsite.entity.Question;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

public interface AskQuestionService {
    Question createQuestion(@Validated @RequestBody Question question);
}
