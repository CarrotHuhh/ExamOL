package com.se.exam.service;
import com.se.exam.po.user;

public interface UserService {
    public user getuser(String useraccount,String password);
    public int getUserByaccount(String useraccount);
    public int signIn(String password, String username, String useraccount);
    public int updateusername(String useraccount,String username);
    public int updateuseraccount(String useraccount,String newaccount);
    public int updatepassword(String useraccount,String password);
}
