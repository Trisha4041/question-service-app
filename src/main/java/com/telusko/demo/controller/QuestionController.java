package com.telusko.demo.controller;

import java.util.List;
import com.telusko.demo.model.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.demo.dao.QuestionDao;
import com.telusko.demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.telusko.demo.dao.QuestionDao;

@RestController
@RequestMapping("/question")
public class QuestionController {
	@Autowired
    private QuestionDao questionDao;
    
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
    	List<Question> questions=questionDao.findAll();

        return ResponseEntity.ok(questions);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        try {
            question.setId(null); // Set to null for new entities
            questionDao.save(question);
            return ResponseEntity.ok("Question added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Failed to add question: " + e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
    	questionDao.save(question);

        return ResponseEntity.ok("Question updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestions(@PathVariable int id){
    	questionDao.deleteById(id);

        return ResponseEntity.ok("Question deleted successfully");
    	
    }
}
