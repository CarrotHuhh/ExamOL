package com.se.exam.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.se.exam.po.user;

@Mapper
public interface UserMapper {
    @Select("select * from user where useraccount=#{useraccount} and password=#{password}")
    public user getuser(String useraccount,String password);
    @Select("select count(*) from user where useraccount=#{useraccount}")
    public int getUserByaccount(String useraccount);
    @Insert("insert into user(password,username,useraccount) values(#{password},#{username},#{useraccount})")
    public int signIn(String password, String username, String useraccount);
}
