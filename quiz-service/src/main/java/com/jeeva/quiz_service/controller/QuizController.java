package com.jeeva.quiz_service.controller;

import com.jeeva.quiz_service.model.QuestionWrapper;
import com.jeeva.quiz_service.model.QuizDto;
import com.jeeva.quiz_service.model.ResponseAns;
import com.jeeva.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService qzService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {
        return qzService.createQuizs(quizDto.getCategory(),quizDto.getNoq(),quizDto.getQzTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQns(@PathVariable int id)
    {
        return qzService.getQzQns(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<ResponseAns> responseAns)
    {
        return qzService.calculateResult(id, responseAns);
    }

}
