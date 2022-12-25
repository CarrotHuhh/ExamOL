package com.se.exam.po;

public class Paper {
    private Integer paperId;
    private String paperName;
    private String choiceAmount;
    private String fillingAmount;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getChoiceAmount() {
        return choiceAmount;
    }

    public void setChoiceAmount(String choiceAmount) {
        this.choiceAmount = choiceAmount;
    }

    public String getFillingAmount() {
        return fillingAmount;
    }

    public void setFillingAmount(String fillingAmount) {
        this.fillingAmount = fillingAmount;
    }
}
