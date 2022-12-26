package com.se.exam.mapper;

import com.se.exam.po.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExamMapper {
    @Select("select * from exam where userId = #{userId}")
    public List<Exam> getExamById(Integer userId);

    @Select("select * from exam where examId = #{examId}")
    public Exam getExamByExamId(Integer examId);

    @Select("select userId from exam where examId = #{examId}")
    public Integer getUserIdByExamId(Integer examId);

    @Select("select examName from exam where examId = #{examId}")
    public String getExamNameByExamId(Integer examId);

    @Update("update exam set examState=1 where examId=#{examId}")
    public Integer updateExamState(Integer examId);
}
