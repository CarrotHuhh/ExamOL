package com.se.exam.service;
import com.se.exam.po.user;

public interface UserService {
    public user getuser(Integer userid,String password);
    public int signIn(String password, String username);
}
