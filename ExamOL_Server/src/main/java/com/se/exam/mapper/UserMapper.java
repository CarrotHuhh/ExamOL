package com.se.exam.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.se.exam.po.user;

@Mapper
public interface UserMapper {
    @Select("select * from user where userid=#{userid} and password=#{password}")
    public user getuser(String userid,String password);
    @Insert("insert into user(password,username) values(#{password},#{username})")
    public user signin(String password,String username);
}
