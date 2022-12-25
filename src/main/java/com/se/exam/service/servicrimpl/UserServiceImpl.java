package com.se.exam.service.servicrimpl;

import com.se.exam.mapper.UserMapper;
import com.se.exam.po.user;
import com.se.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public user getuser(String useraccount, String password) {
        return userMapper.getuser(useraccount, password);
    }
    
    @Override
    public int getUserByaccount(String useraccount) {
    	return userMapper.getUserByaccount(useraccount);
    }

    @Override
    public int signIn(String password, String username, String useraccount) {
        return userMapper.signIn(password, username, useraccount);
    }
}