package com.jeeva.question_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private String questionTitle;   // actual question
    private String answer;
    private String category;        //java or c or python
    private String difficultyLevel; // easy or medium or hard
}
