package com.telusko.demo.dao;

import com.telusko.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    
    // Find questions by category
    List<Question> findByCategory(String category);
    
    // Find random questions by category with limit
    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", 
           nativeQuery = true)
    List<Question> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);
    
    // Alternative method if you want to use JPQL
    @Query("SELECT q FROM Question q WHERE q.category = :category ORDER BY FUNCTION('RANDOM')")
    List<Question> findRandomQuestionsByCategoryJPQL(@Param("category") String category);
}