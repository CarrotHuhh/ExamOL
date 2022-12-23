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
    public user getUser(String userid, String password)throws Exception{
        return userService.getuser(userid,password);
    }

    @RequestMapping("/signin")
    public user signin(String password,String username)throws Exception{
        return userService.signin(password,username);
    }
}