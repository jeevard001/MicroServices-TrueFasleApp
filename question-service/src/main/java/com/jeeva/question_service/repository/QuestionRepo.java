package com.jeeva.question_service.repository;


import com.jeeva.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    public List<Question> findByCategory(String category);

    @Query(value ="SELECT q.id FROM question q WHERE q.category=:category ORDER BY RAND() limit :noq;", nativeQuery = true )
    public List<Integer> getQuizQns(String category, Integer noq);
}
