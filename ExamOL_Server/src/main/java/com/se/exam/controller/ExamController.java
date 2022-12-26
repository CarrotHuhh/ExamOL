package com.se.exam.controller;

import com.se.exam.po.Exam;
import com.se.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ExamController")
public class ExamController {
    @Autowired
    private ExamService examService;
    @RequestMapping("/getExamById")
    public List<Exam> getExamById(Integer userId){
        return examService.getExamById(userId);
    }
    @RequestMapping("/updateExamState")
    public Integer updateExamState(Integer examId) {
    	return examService.updateExamState(examId);
    }
}
