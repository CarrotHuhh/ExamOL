package com.se.exam.controller;

import com.se.exam.po.Submission;
import com.se.exam.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SubmissionController")
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;

    @RequestMapping("/submit")
    public int submit(List<Submission> submissions) {
        return submissionService.submit(submissions);
    }
    @RequestMapping("/grade")
    public int grade(Integer examId){
        return submissionService.grade(examId);
    }
}
