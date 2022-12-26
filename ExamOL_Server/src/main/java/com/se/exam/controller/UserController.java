package com.se.exam.controller;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.exam.po.user;
import com.se.exam.service.UserService;

@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public user getUser(String useraccount, String password)throws Exception{
        return userService.getuser(useraccount,password);
    }
    
    @RequestMapping("/getUserByaccount")
    public int getUserByaccount(String useraccount)throws Exception{
    	return userService.getUserByaccount(useraccount);
    }

    @RequestMapping("/signIn")
    public int signIn(String password,String username, String useraccount)throws Exception{
        return userService.signIn(password,username,useraccount);
    }
    @RequestMapping("/updateusername")
    public int updateusername(String useraccount,String username)throws Exception {
        return userService.updateusername(useraccount,username);
    }
    @RequestMapping("/updateuseraccount")
    public int updateuseraccount(String useraccount,String newaccount)throws Exception{
        return userService.updateuseraccount(useraccount,newaccount);
    }
    @RequestMapping("/updatepassword")
    public int updatepassword(String useraccount,String password)throws Exception{
        return userService.updatepassword(useraccount,password);
    }
}
