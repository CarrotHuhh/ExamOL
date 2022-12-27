package com.se.exam.mapper;

import com.se.exam.po.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaperMapper {
    @Select("select * from paper where paperId=#{paperId}")
    public Paper getPaperInfoById(Integer paperId);
}
