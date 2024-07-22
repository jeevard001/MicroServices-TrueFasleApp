package com.jeeva.quizApp.controller;

import com.jeeva.quizApp.model.Question;
import com.jeeva.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService qservice;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>>  getAllQuestion()
    {
        return qservice.getAllQuestions();
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
       return qservice.addQuestions(question);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>>  getByCategory(@PathVariable String category)
    {
        return qservice.getByCategories(category);
    }
}
