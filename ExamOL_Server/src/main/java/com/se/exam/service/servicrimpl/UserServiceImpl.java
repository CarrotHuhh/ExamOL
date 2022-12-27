package com.se.exam.service.servicrimpl;

import com.se.exam.mapper.UserMapper;
import com.se.exam.po.User;
import com.se.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String userAccount, String password) {
        return userMapper.getuser(userAccount, password);
    }

    @Override
    public int getUserByAccount(String userAccount) {
        return userMapper.getUserByaccount(userAccount);
    }

    @Override
    public int signIn(String password, String username, String userAccount) {
        return userMapper.signIn(password, username, userAccount);
    }

    @Override
    public int updateUsername(String userAccount, String username) {
        return userMapper.updateusername(userAccount, username);
    }

    @Override
    public int updateUserAccount(String userAccount, String newAccount) {
        return userMapper.updateuseraccount(userAccount, newAccount);
    }

    @Override
    public int updatePassword(String useraccount, String password) {
        return userMapper.updatepassword(useraccount, password);
    }
}
