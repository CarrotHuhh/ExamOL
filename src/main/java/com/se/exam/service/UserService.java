package com.se.exam.service;
import com.se.exam.po.user;

public interface UserService {
    public user getuser(String useraccount,String password);
    public int getUserByaccount(String useraccount);
    public int signIn(String password, String username, String useraccount);
}
