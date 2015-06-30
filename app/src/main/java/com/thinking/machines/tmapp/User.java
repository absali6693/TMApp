package com.thinking.machines.tmapp;

/**
 * Created by Abbas on 4/27/2015.
 */
public class User {
    private int id;
    private String username;
    private String mobileNumber;
    private String userId;
    private String startupStatus;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getStartupStatus() {
        return startupStatus;
    }
    public void setStartupStatus(String startupStatus) {
        this.startupStatus = startupStatus;
    }

}
