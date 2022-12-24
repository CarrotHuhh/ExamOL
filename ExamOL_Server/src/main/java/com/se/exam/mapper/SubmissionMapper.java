package com.se.exam.mapper;

import com.se.exam.po.Submission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface SubmissionMapper {
    @Insert("insert into submission(submissionId,examId,questionId,answer,isGraded,type) values(null,#{examId},#{questionId},#{answer},0,#{type})")
    public int submit(Submission submission);

    @Select("select * from submission where examId=#{examId}")
    public List<Submission> getSubmissionByExamId(Integer examId);
}
