package com.se.exam.po;

public class user {
    private String username;
    private Integer userid;
    private String password;
    private String useraccount;

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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    public String getUseraccount() {
    	return useraccount;
    }
    
    public void setUseraccount(String useraccount) {
    	this.useraccount = useraccount;
    }
}