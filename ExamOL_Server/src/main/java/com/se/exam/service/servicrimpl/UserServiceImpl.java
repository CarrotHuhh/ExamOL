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
    public int signIn(String password, String username, String useraccount) {return userMapper.signIn(password, username, useraccount);}

    @Override
    public int updateusername(String useraccount,String username){return userMapper.updateusername(useraccount,username);}

    @Override
    public int updateuseraccount(String useraccount,String newaccount){return userMapper.updateuseraccount(useraccount,newaccount);}

    @Override
    public int updatepassword(String useraccount,String password){return userMapper.updatepassword(useraccount,password);}
}
