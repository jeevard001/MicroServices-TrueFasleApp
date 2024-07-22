package com.jeeva.quizApp.model;

import lombok.Data;

@Data
public class QuestionWrapper {
    private long id;
    private String questionTitle;

    public QuestionWrapper(long id, String questionTitle) {
        this.id = id;
        this.questionTitle = questionTitle;
    }
}
