package com.se.exam.po;

public class Exam {
    private Integer examId;
    private String examName;
    private Integer paperId;
    private Integer userId;
    private Integer choiceScore;
    private Integer fillingScore;

    public Integer getChoiceScore() {
        return choiceScore;
    }

    public void setChoiceScore(Integer choiceScore) {
        this.choiceScore = choiceScore;
    }

    public Integer getFillingScore() {
        return fillingScore;
    }

    public void setFillingScore(Integer fillingScore) {
        this.fillingScore = fillingScore;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}