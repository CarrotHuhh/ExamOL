package com.se.exam.service.servicrimpl;

import com.se.exam.mapper.*;
import com.se.exam.po.Record;
import com.se.exam.po.Submission;
import com.se.exam.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    SubmissionMapper submissionMapper;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    ExamMapper examMapper;
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public int submit(List<Submission> submissions) {
        Record record = new Record();
        record.setExamId(submissions.get(0).getExamId());
        record.setUserId(examMapper.getUserIdByExamId(record.getExamId()));
        record.setExamName(examMapper.getExamNameByExamId(record.getExamId()));
        recordMapper.saveRecord(record);
        int rows = 0;
        for (Submission submission : submissions) {
            rows += submissionMapper.submit(submission);
        }
        return rows;
    }

    @Override
    public int grade(Integer examId) {
        List<Submission> submissions = submissionMapper.getSubmissionByExamId(examId);
        Integer grade = 0;
        for (Submission submission : submissions) {
            String answer = questionMapper.getAnswerById(submission.getQuestionId());
            if(answer.equals(submission.getAnswer())&&submission.getType()==0){
                grade+=examMapper.getExamByExamId(submission.getExamId()).getChoiceScore();
            }
            else if(answer.equals(submission.getAnswer())&&submission.getType()==1){
                grade+=examMapper.getExamByExamId(submission.getExamId()).getFillingScore();
            }
        }
        return recordMapper.grade(grade,examId);
    }
}
