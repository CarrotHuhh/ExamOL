package com.se.exam.service;

import com.se.exam.po.Paper;
import com.se.exam.po.Question;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PaperService {
    public Paper getPaperInfoById(Integer paperId);
    public List<Question> getPaperChoice(Integer paperId);
    public List<Question> getPaperFilling(Integer paperId);
}
