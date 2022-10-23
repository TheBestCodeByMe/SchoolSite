package com.example.schoolsite.controllers;

import com.example.schoolsite.entity.Question;
import com.example.schoolsite.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/askQuestion")
public class AskQuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getQuestionDetails/{code}")
    public Question getQuestion(@PathVariable(value = "code") String code) throws InterruptedException, ExecutionException {
        return questionService.getQuestionDetails(code);
    }

    @PostMapping("/addQuestion")
    public Question createQuestion(@RequestBody Question question) throws ExecutionException, InterruptedException {
        question.setCode(question.getQuestion().substring(10, 15).concat("question" + Math.random()));
        questionService.saveQuestion(question);
        return question;
    }

   // @PostMapping("/addQuestion")
    //public String createQuestion(@Validated @RequestBody Question question) throws InterruptedException, ExecutionException {
    //    question.setCode(question.getQuestion().substring(10, 15).concat("question" + Math.random()));
    //    return questionService.saveQuestion(question);
   // }

    @PostMapping("/updateQuestion")
    public String updateQuestion(@RequestBody Question question) throws InterruptedException, ExecutionException {
        return questionService.updateQuestionDetails(question);
    }

    @DeleteMapping("/deleteQuestion/{code}")
    public String deleteQuestion(@PathVariable(value = "code")  String code) {
        return questionService.deleteQuestion(code);
    }
    /*
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
    */
}
