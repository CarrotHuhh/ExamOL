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
<<<<<<< HEAD
        //创建考试记录
=======
>>>>>>> 298c452 (fzh-1226)
        Record record = new Record();
        record.setExamId(submissions.get(0).getExamId());
        record.setUserId(examMapper.getUserIdByExamId(record.getExamId()));
        record.setExamName(examMapper.getExamNameByExamId(record.getExamId()));
        recordMapper.saveRecord(record);
        int rows = 0;
        for (Submission submission : submissions) {
<<<<<<< HEAD
            //提交每题答题情况
=======
>>>>>>> 298c452 (fzh-1226)
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
<<<<<<< HEAD
=======
                submissionMapper.updateGraded(submission.getSubmissionId());
>>>>>>> 298c452 (fzh-1226)
            }
            else if(answer.equals(submission.getAnswer())&&submission.getType()==1){
                grade+=examMapper.getExamByExamId(submission.getExamId()).getFillingScore();
            }
        }
        return recordMapper.grade(grade,examId);
    }
<<<<<<< HEAD
=======
    
    @Override
    public int updateGraded(Integer submissionId) {
    	return submissionMapper.updateGraded(submissionId);
    }
>>>>>>> 298c452 (fzh-1226)
}
