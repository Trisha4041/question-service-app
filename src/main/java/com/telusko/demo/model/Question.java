package com.telusko.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "question_text")
    private String questionTitle;
    
    @Column(name = "option_a")
    private String option1;
    
    @Column(name = "option_b")
    private String option2;
    
    @Column(name = "option_c")
    private String option3;
    
    @Column(name = "option_d")
    private String option4;
    
    @Column(name = "correct_option")
    private String rightAnswer;
    
    @Column(name = "category")
    private String category;
    
    // Default constructor
    public Question() {}
    
    // Constructor with all fields
    public Question(String questionTitle, String option1, String option2, 
                   String option3, String option4, String rightAnswer, String category) {
        this.questionTitle = questionTitle;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.rightAnswer = rightAnswer;
        this.category = category;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getQuestionTitle() {
        return questionTitle;
    }
    
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    
    public String getOption1() {
        return option1;
    }
    
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    
    public String getOption2() {
        return option2;
    }
    
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    
    public String getOption3() {
        return option3;
    }
    
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    
    public String getOption4() {
        return option4;
    }
    
    public void setOption4(String option4) {
        this.option4 = option4;
    }
    
    public String getRightAnswer() {
        return rightAnswer;
    }
    
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
}