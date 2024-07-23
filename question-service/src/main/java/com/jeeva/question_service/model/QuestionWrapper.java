package com.jeeva.question_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionWrapper {
    private long id;
    private String questionTitle;

    public QuestionWrapper(long id, String questionTitle) {
        this.id = id;
        this.questionTitle = questionTitle;
    }
}
