package com.se.exam.mapper;

import com.se.exam.po.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from question where paperId=#{paperId} and type=0")
    public List<Question> getPaperChoice(Integer paperId);

    @Select("select * from question where paperId=#{paperId} and type=1")
    public List<Question> getPaperFilling(Integer paperId);

    @Select("select answer from question where questionId=#{questionId}")
    public String getAnswerById(Integer questionId);
}
