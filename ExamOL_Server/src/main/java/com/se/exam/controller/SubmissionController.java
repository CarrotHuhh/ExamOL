package com.se.exam.controller;

import com.se.exam.po.Submission;
import com.se.exam.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SubmissionController")
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;

    @RequestMapping("/submit")
    public int submit(@RequestBody Map<String, List<Submission>> sub) {
    	List<Submission> submissions = sub.get("submissions");
        return submissionService.submit(submissions);
    }
    @RequestMapping("/grade")
    public int grade(Integer examId){
        return submissionService.grade(examId);
    }
}
