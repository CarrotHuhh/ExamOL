package com.se.exam.service;

import com.se.exam.po.Exam;

import java.util.List;

public interface ExamService {
    public List<Exam> getExamById(Integer userId);
    public Integer updateExamState(Integer examId);
<<<<<<< HEAD
=======
    public Exam getExamByExamId(Integer examId);
>>>>>>> 112e1e8 (fzh-added)
}
