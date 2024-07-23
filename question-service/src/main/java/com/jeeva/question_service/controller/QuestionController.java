package com.jeeva.question_service.controller;


import com.jeeva.question_service.model.Question;
import com.jeeva.question_service.model.QuestionWrapper;
import com.jeeva.question_service.model.ResponseAns;
import com.jeeva.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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


    //generate ->for the requests from the quiz to the questions.
    @GetMapping("generate")
    public  ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQns)
    {
        return qservice.getQuestionsForQuizz(category, numQns);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        return  qservice.getQuestionsFromIds(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseAns> respones)
    {
        return qservice.getScore(respones);
    }
}
