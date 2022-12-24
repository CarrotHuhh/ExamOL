package com.se.exam.mapper;

import com.se.exam.po.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RecordMapper {
    @Insert("insert into record(examId,userId,examName,score) values(#{examId},#{userId},#{examName},0)")
    public int saveRecord(Record record);

    @Update("update record set score=#{grade} where examId=#{examId}")
    public int grade(Integer grade, Integer examId);
}
