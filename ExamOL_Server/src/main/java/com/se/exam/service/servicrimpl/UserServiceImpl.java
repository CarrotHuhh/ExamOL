package com.se.exam.service.servicrimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.exam.mapper.UserMapper;
import com.se.exam.po.user;
import com.se.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public user getuser(String userid, String password){
        return userMapper.getuser(userid,password);
    }
    @Override
    public user signin(String password,String username){
        return userMapper.signin(password,username);
    }
}
