package com.se.exam.controller;

import com.se.exam.po.Submission;
import com.se.exam.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
=======
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
>>>>>>> 298c452 (fzh-1226)

@RestController
@RequestMapping("/SubmissionController")
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;

    @RequestMapping("/submit")
<<<<<<< HEAD
    public int submit(List<Submission> submissions) {
=======
    public int submit(@RequestBody Map<String, List<Submission>> sub) {
    	List<Submission> submissions = sub.get("submissions");
>>>>>>> 298c452 (fzh-1226)
        return submissionService.submit(submissions);
    }
    @RequestMapping("/grade")
    public int grade(Integer examId){
        return submissionService.grade(examId);
    }
}
