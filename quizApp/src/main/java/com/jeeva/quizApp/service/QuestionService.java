package com.jeeva.quizApp.service;

import com.jeeva.quizApp.model.Question;
import com.jeeva.quizApp.repository.QuestionRepo;
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
}
