package com.se.exam.controller;
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
}
