package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDto implements Serializable {
    private String address;
    private String code;
    private Date dateOfBirth;
    private String defCode;

    private String email;
    private String firstName;
    private String gender;
    private String identityCardCardNumber;
    private String identityCardIssuedBy;
    private Date identityCardIssuedDate;
    private String jobPositionCode;
    private String leaveDate;
    private String name;
    private String phone;
    private String startDate;
    private Integer status;
    private String userCode;
    private int id;
    private List<DocumentDto> lstDocuments;
    private int deptId;
    private String deptName;
    private String userName;
    private String fullName;
    private String password;
    private int userType;

    public EmployeeDto() {
    }

    public EmployeeDto(String address, String code, Date dateOfBirth, String defCode, String email, String firstName, String gender, String identityCardCardNumber, String identityCardIssuedBy, Date identityCardIssuedDate, String jobPositionCode, String leaveDate, String name, String phone, String startDate, Integer status, String userCode, int id, List<DocumentDto> lstDocuments, int deptId, String deptName, String userName, String fullName, String password, int userType) {
        this.address = address;
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.defCode = defCode;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.identityCardCardNumber = identityCardCardNumber;
        this.identityCardIssuedBy = identityCardIssuedBy;
        this.identityCardIssuedDate = identityCardIssuedDate;
        this.jobPositionCode = jobPositionCode;
        this.leaveDate = leaveDate;
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.status = status;
        this.userCode = userCode;
        this.id = id;
        this.lstDocuments = lstDocuments;
        this.deptId = deptId;
        this.deptName = deptName;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.userType = userType;
    }

    public EmployeeDto clone(){
        return new EmployeeDto(address, code, dateOfBirth, defCode, email,
                firstName, gender, identityCardCardNumber, identityCardIssuedBy,
                identityCardIssuedDate, jobPositionCode, leaveDate, name, phone,
                startDate, status, userCode, id, lstDocuments, deptId, deptName,
                userName, fullName, password, userType);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentityCardCardNumber() {
        return identityCardCardNumber;
    }

    public void setIdentityCardCardNumber(String identityCardCardNumber) {
        this.identityCardCardNumber = identityCardCardNumber;
    }

    public String getIdentityCardIssuedBy() {
        return identityCardIssuedBy;
    }

    public void setIdentityCardIssuedBy(String identityCardIssuedBy) {
        this.identityCardIssuedBy = identityCardIssuedBy;
    }

    public Date getIdentityCardIssuedDate() {
        return identityCardIssuedDate;
    }

    public void setIdentityCardIssuedDate(Date identityCardIssuedDate) {
        this.identityCardIssuedDate = identityCardIssuedDate;
    }

    public String getJobPositionCode() {
        return jobPositionCode;
    }

    public void setJobPositionCode(String jobPositionCode) {
        this.jobPositionCode = jobPositionCode;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DocumentDto> getLstDocuments() {
        if(lstDocuments == null){
            lstDocuments = new ArrayList<>();
        }
        return lstDocuments;
    }

    public void setLstDocuments(List<DocumentDto> lstDocuments) {
        this.lstDocuments = lstDocuments;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
}
