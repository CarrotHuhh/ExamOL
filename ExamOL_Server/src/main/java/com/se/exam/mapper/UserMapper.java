package com.se.exam.mapper;

import com.se.exam.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where useraccount=#{useraccount} and password=#{password}")
    public User getuser(String useraccount, String password);

    @Select("select count(*) from user where useraccount=#{useraccount}")
    public int getUserByaccount(String useraccount);

    @Insert("insert into user(password,username,useraccount) values(#{password},#{username},#{useraccount})")
    public int signIn(String password, String username, String useraccount);

    @Update("update user set username=#{username} where useraccount=#{useraccount}")
    public int updateusername(String useraccount, String username);

    @Update("update user set useraccount=#{newaccount} where useraccount=#{useraccount}")
    public int updateuseraccount(String useraccount, String newaccount);

    @Update("update user set password=#{password} where useraccount=#{useraccount}")
    public int updatepassword(String useraccount, String password);
}
