package com.telusko.demo.model;

public class ResponseWrapper {
    private Integer questionId;
    private String response;

    public ResponseWrapper() {}

    public ResponseWrapper(Integer questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
