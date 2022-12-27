package com.se.exam.service.servicrimpl;

import com.se.exam.mapper.PaperMapper;
import com.se.exam.mapper.QuestionMapper;
import com.se.exam.po.Paper;
import com.se.exam.po.Question;
import com.se.exam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public Paper getPaperInfoById(Integer paperId) {
        return paperMapper.getPaperInfoById(paperId);
    }

    @Override
    public List<Question> getPaperChoice(Integer paperId) {
        return questionMapper.getPaperChoice(paperId);
    }

    @Override
    public List<Question> getPaperFilling(Integer paperId) {
        return questionMapper.getPaperFilling(paperId);
    }
}
