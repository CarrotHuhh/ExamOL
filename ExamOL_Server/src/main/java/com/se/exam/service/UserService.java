package com.se.exam.service;

import com.se.exam.po.User;

public interface UserService {
    public User getUser(String userAccount, String password);

    public int getUserByAccount(String userAccount);

    public int signIn(String password, String username, String userAccount);

    public int updateUsername(String userAccount, String username);

    public int updateUserAccount(String userAccount, String newAccount);

    public int updatePassword(String userAccount, String password);
}
