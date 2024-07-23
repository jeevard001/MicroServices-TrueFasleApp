package com.jeeva.quiz_service.service;

import com.jeeva.quiz_service.feign.QuizInterface;
import com.jeeva.quiz_service.model.QuestionWrapper;
import com.jeeva.quiz_service.model.Quiz;
import com.jeeva.quiz_service.model.ResponseAns;
import com.jeeva.quiz_service.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepo qzRepo;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuizs(String category, int noq, String qtitle) {

        // since the method returns responseEntity, we use the getBody() to get the integers.
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,noq).getBody();
        Quiz quiz = new Quiz();
        quiz.setQtitle(qtitle);
        quiz.setQuestionIds(questions);
        qzRepo.save(quiz);


        return new ResponseEntity<>("Successfuly saved!!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQzQns(int id) {

        Quiz quiz=qzRepo.findById(id).get();  // we can simply use .get() to avoid the optional...
        List<Integer> questionIds = quiz.getQuestionIds();
       ResponseEntity<List<QuestionWrapper>>  questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseAns> responseAns) {

        ResponseEntity<Integer> score = quizInterface.getScore(responseAns);
        return score;
    }
}
