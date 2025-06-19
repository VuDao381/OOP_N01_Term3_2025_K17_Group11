package com.example.servingwebcontent;
// User.java
public class User {
    private String username;
    private static int uid = 1;
    private int userid;
    private String useremail;
    private String userpassword;

    public User(String name, String email, String password) {
        this.username = name;
        this.userid = uid++;
        this.useremail = email;
        this.userpassword = password;
    }

    public String getName() {
        return username;
    }

    public int getUID() {
        return userid;
    }

    public String getEmail() {
        return useremail;
    }

    public String getPassword() {
        return userpassword;
    }

    public void setUser(String name, String email, String password) {
        this.username = name;
        this.useremail = email;
        this.userpassword = password;
    }

}

