package com.jeeva.quiz_service.model;

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

//    @ManyToMany
//    private List<Question> questions;

//since we are using the collection of elements and not the entity, we simply use the @ElementCollection
    @ElementCollection
    private List<Integer> questionIds;
}
