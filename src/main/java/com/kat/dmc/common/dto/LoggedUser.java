package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.List;

public class LoggedUser implements Serializable {
    private String username;
    private String empName;
    private String mainDeptName;
    private String mainJobName;
    private Integer mainDeptId;
    private Integer mainJobId;
    private String loggedTime;
    private Integer userType;
    private Integer userId;
    private List<ObjectDto> lstPermission;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getMainDeptName() {
        return mainDeptName;
    }

    public void setMainDeptName(String mainDeptName) {
        this.mainDeptName = mainDeptName;
    }

    public String getMainJobName() {
        return mainJobName;
    }

    public void setMainJobName(String mainJobName) {
        this.mainJobName = mainJobName;
    }

    public Integer getMainDeptId() {
        return mainDeptId;
    }

    public void setMainDeptId(Integer mainDeptId) {
        this.mainDeptId = mainDeptId;
    }

    public Integer getMainJobId() {
        return mainJobId;
    }

    public void setMainJobId(Integer mainJobId) {
        this.mainJobId = mainJobId;
    }

    public String getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(String loggedTime) {
        this.loggedTime = loggedTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ObjectDto> getLstPermission() {
        return lstPermission;
    }

    public void setLstPermission(List<ObjectDto> lstPermission) {
        this.lstPermission = lstPermission;
    }
}
