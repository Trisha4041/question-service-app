package com.telusko.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.demo.dao.QuestionDao;
import com.telusko.demo.dao.QuizDao;
import com.telusko.demo.model.Question;
import com.telusko.demo.model.QuestionWrapper;
import com.telusko.demo.model.Quiz;
import com.telusko.demo.model.Response;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuizDao quizDao;

    // 1. Create a quiz
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);

            if (questions.isEmpty()) {
                return ResponseEntity.badRequest().body("No questions found for category: " + category);
            }

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);

            return ResponseEntity.ok("Quiz created successfully with ID: " + quiz.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating quiz: " + e.getMessage());
        }
    }

    // 2. Get questions for quiz (without answers)
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try {
            Optional<Quiz> quizOpt = quizDao.findById(id);

            if (!quizOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            List<Question> questions = quizOpt.get().getQuestions();
            List<QuestionWrapper> wrappers = new ArrayList<>();

            for (Question q : questions) {
                QuestionWrapper wrapper = new QuestionWrapper(
                        q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()
                );
                wrappers.add(wrapper);
            }

            return ResponseEntity.ok(wrappers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 3. Calculate score based on user responses
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            Optional<Quiz> quizOpt = quizDao.findById(id);

            if (!quizOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            List<Question> questions = quizOpt.get().getQuestions();

            if (responses == null || responses.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Map questions by ID for fast lookup
            Map<Integer, Question> questionMap = new HashMap<>();
            for (Question q : questions) {
                questionMap.put(q.getId(), q);
            }

            int score = 0;
            for (Response userResponse : responses) {
                Question question = questionMap.get(userResponse.getId());
                if (question != null && userResponse.getResponse() != null &&
                    userResponse.getResponse().equalsIgnoreCase(question.getRightAnswer())) {
                    score++;
                }
            }

            return ResponseEntity.ok(score);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
