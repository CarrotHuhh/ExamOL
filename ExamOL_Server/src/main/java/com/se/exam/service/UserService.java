package com.se.exam.service;
import com.se.exam.po.user;

public interface UserService {
    public user getuser(String userid,String password);
    public user signin(String password,String username);
}
