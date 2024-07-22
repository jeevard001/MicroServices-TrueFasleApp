package com.jeeva.quizApp.repository;

import com.jeeva.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
    public List<Question> findByCategory(String category);

    @Query(value ="SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() limit :noq;", nativeQuery = true )
    public List<Question> getQuizQns(String category, int noq);
}
