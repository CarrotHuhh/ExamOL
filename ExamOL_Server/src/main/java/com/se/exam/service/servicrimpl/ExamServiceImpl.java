package com.se.exam.service.servicrimpl;

import com.se.exam.mapper.ExamMapper;
import com.se.exam.mapper.SubmissionMapper;
import com.se.exam.po.Exam;
import com.se.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Override
    public List<Exam> getExamById(Integer userId) {
        return examMapper.getExamById(userId);
    }
<<<<<<< HEAD
=======

>>>>>>> 112e1e8 (fzh-added)
    @Override
    public Integer updateExamState(Integer examId) {
    	return examMapper.updateExamState(examId);
    }
<<<<<<< HEAD
=======
    
    @Override
    public Exam getExamByExamId(Integer examId) {
    	return examMapper.getExamByExamId(examId);
    }
>>>>>>> 112e1e8 (fzh-added)
}
