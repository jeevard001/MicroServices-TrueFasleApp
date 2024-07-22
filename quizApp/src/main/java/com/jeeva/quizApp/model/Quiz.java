package com.jeeva.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String qtitle;
    @ManyToMany
    private List<Question> questions;
}
