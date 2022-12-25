package com.se.exam.po;

public class user {
    private String username;
    private Integer userId;
    private String password;
    private String userAccount;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserAccount() {
    	return userAccount;
    }
    
    public void setUserAccount(String userAccount) {
    	this.userAccount = userAccount;
    }
}
