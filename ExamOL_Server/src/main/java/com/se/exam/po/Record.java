package com.se.exam.po;

public class Record {
    private Integer userId;
    private Integer examId;
    private String examName;
    private Integer score;
    private Integer min;
    private Integer sec;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    
    public Integer getMin() {
    	return min;
    }
    
    public void setMin(Integer min) {
    	this.min = min;
    }
    
    public Integer getSec() {
    	return sec;
    }
    
    public void setSec(Integer sec) {
    	this.sec = sec;
    }
}
