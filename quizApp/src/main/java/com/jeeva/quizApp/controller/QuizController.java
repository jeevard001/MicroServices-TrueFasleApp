package com.jeeva.quizApp.controller;

import com.jeeva.quizApp.model.QuestionWrapper;
import com.jeeva.quizApp.model.ResponseAns;
import com.jeeva.quizApp.service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noq, @RequestParam String qtitle)
    {
        return qzService.createQuizs(category,noq,qtitle);
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
