package com.se.exam.mapper;

import com.se.exam.po.Record;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordMapper {
    @Insert("insert into record(examId,userId,examName,score) values(#{examId},#{userId},#{examName},0)")
    public int saveRecord(Record record);

    @Update("update record set score=#{grade} where examId=#{examId}")
    public int grade(Integer grade, Integer examId);
    
    @Select("select * from record where userId=#{userId}")
    public List<Record> getRecord(Integer userId);
    
    @Update("update record set min=#{min}, sec=#{sec} where examId=#{examId}")
    public int setTime(Integer min, Integer sec, Integer examId);
}
