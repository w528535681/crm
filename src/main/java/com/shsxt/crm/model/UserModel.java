package com.shsxt.crm.model;

public class UserModel {

    private Integer userId;
    private String userName;
    private String trueName;

    public UserModel() {
    }

    public UserModel(Integer userId, String userName, String trueName) {
        this.userId = userId;
        this.userName = userName;
        this.trueName = trueName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

}
