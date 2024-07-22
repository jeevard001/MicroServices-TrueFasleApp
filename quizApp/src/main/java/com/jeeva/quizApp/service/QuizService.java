package com.jeeva.quizApp.service;

import com.jeeva.quizApp.model.Question;
import com.jeeva.quizApp.model.QuestionWrapper;
import com.jeeva.quizApp.model.Quiz;
import com.jeeva.quizApp.model.ResponseAns;
import com.jeeva.quizApp.repository.QuestionRepo;
import com.jeeva.quizApp.repository.QuizRepo;
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
    QuestionRepo qrepo;

    public ResponseEntity<String> createQuizs(String category, int noq, String qtitle) {

        Quiz quiz = new Quiz();
        quiz.setQtitle(qtitle);
        quiz.setQuestions(qrepo.getQuizQns(category,noq));
        qzRepo.save(quiz);

        return new ResponseEntity<>("Successfuly saved!!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQzQns(int id) {

        Optional<Quiz> quiz=qzRepo.findById(id);  // we can simply use .get() to avoid the optional...

        List<Question> qnsFromDB =quiz.get().getQuestions();
        List<QuestionWrapper> qnsForUsers = new ArrayList<>();

        for (Question q : qnsFromDB)
        {
            QuestionWrapper qw =new QuestionWrapper(q.getId(),q.getQuestionTitle());
            qnsForUsers.add(qw);
        }


        return new ResponseEntity<>(qnsForUsers, HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseAns> responseAns) {
        Quiz quiz= qzRepo.findById(id).get();
        List<Question> questions = quiz.getQuestions();
                        //id and response
        int i=0,right=0;
        for(ResponseAns responseAns1 : responseAns)
        {
            if(responseAns1.getResponse().equals(questions.get(i).getAnswer())) right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
