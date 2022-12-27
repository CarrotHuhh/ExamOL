package com.se.exam.service;

import com.se.exam.po.Submission;

import java.util.List;

public interface SubmissionService {
    public int submit(List<Submission> submissions);

    public int grade(Integer examId);
}
