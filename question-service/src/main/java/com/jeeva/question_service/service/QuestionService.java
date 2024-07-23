package com.jeeva.question_service.service;


import com.jeeva.question_service.model.Question;
import com.jeeva.question_service.model.QuestionWrapper;
import com.jeeva.question_service.model.ResponseAns;
import com.jeeva.question_service.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    QuestionRepo qrepo;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(qrepo.findAll() , HttpStatus.OK) ;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;

    }

    public ResponseEntity<String> addQuestions(Question question)
    {
        qrepo.save(question);
        return new ResponseEntity<>( "Success" , HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getByCategories(String category)
    {
        try {
            return new ResponseEntity<>(qrepo.findByCategory(category), HttpStatus.OK) ;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;

    }

    public ResponseEntity<List<Integer>> getQuestionsForQuizz(String category, Integer numQns) {

        List<Integer> questions = qrepo.getQuizQns(category,numQns);
        return  new ResponseEntity<>(questions, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> qWrappers = new ArrayList<>();
        List<Question> qns = new ArrayList<>();

        for(Integer id : questionIds)
        {
            qns.add(qrepo.findById(Long.valueOf(id)).get());
        }

        for(Question question : qns)
        {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            qWrappers.add(wrapper);
        }

        return new ResponseEntity<>(qWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<ResponseAns> respones) {
        int right=0;
        for(ResponseAns res : respones)
        {
            Question question = qrepo.findById(Long.valueOf(res.getId())).get();
            if(res.getResponse().equals(question.getAnswer())) right++;
        }
        return  new ResponseEntity<>(right, HttpStatus.OK);
    }
}
