package com.example.chattingfirebase.userlist;

public class User {
    private String userName;
    private String description;

    private String userId;


    public User() {
    }

    public User(String name, String description) {
        this.userName = name;
        this.description = description;
    }

    public User(String name, String description, String UID) {
        this.userName = name;
        this.description = description;
        this.userId = UID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

