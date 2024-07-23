package com.jeeva.quiz_service.model;

import lombok.Data;

@Data  // for getters and setters.
public class QuizDto {
    String category;
    Integer noq;
    String qzTitle;
}
