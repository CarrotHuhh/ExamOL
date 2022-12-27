package com.se.exam.mapper;

import com.se.exam.po.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Insert("insert into record(examId,userId,examName,score,min,sec) values(#{examId},#{userId},#{examName},score,null,null)")
    public int saveRecord(Record record);

    @Update("update record set score=#{grade} where examId=#{examId}")
    public int grade(Integer grade, Integer examId);

    @Select("select * from record where userId=#{userId}")
    public List<Record> getRecord(Integer userId);

    @Update("update record set min=#{min}, sec=#{sec} where examId=#{examId}")
    public int setTime(Integer min, Integer sec, Integer examId);
}
