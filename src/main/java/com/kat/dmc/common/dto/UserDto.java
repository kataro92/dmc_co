package com.kat.dmc.common.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    private int userId;
    private String userName;
    private String fullName;
    private String password;
    private int userType;
    private int status;
    private String code;

    public UserDto() {
    }

    public UserDto(int userId, String userName, String fullName, String password, int userType, int status, String code) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.userType = userType;
        this.status = status;
        this.code = code;
    }

    @Override
    public UserDto clone(){
        return new UserDto(this.userId, this.userName, this.fullName, this.password, this.userType, this.status, this.code);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
