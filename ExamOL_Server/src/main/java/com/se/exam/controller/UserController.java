package com.se.exam.controller;

import com.se.exam.po.User;
import com.se.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(String useraccount, String password) throws Exception {
        return userService.getUser(useraccount, password);
    }

    @RequestMapping("/getUserByaccount")
    public int getUserByaccount(String useraccount) throws Exception {
        return userService.getUserByAccount(useraccount);
    }

    @RequestMapping("/signIn")
    public int signIn(String password, String username, String useraccount) throws Exception {
        return userService.signIn(password, username, useraccount);
    }

    @RequestMapping("/updateusername")
    public int updateusername(String useraccount, String username) throws Exception {
        return userService.updateUsername(useraccount, username);
    }

    @RequestMapping("/updateuseraccount")
    public int updateuseraccount(String useraccount, String newaccount) throws Exception {
        return userService.updateUserAccount(useraccount, newaccount);
    }

    @RequestMapping("/updatepassword")
    public int updatepassword(String useraccount, String password) throws Exception {
        return userService.updatePassword(useraccount, password);
    }
}
