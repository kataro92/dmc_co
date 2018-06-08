package com.kat.dmc.common.model;

import java.io.Serializable;

public class ClientDto  implements Serializable{
    private String address;
    private String code;
    private String contactPerson;
    private String defCode;
    private String email;
    private String employeeCode;
    private String fax;
    private String name;
    private String phone;
    private Integer status;
    private String taxCode;
    private String trademark;
    private int id;
    private Integer empId;
    private Integer deptId;
    private String empName;
    private String deptName;

    public ClientDto() {
    }

    public ClientDto(String address, String code, String contactPerson, String defCode,
                     String email, String employeeCode, String fax, String name, String phone,
                     Integer status, String taxCode, String trademark, int id, Integer empId,
                     Integer deptId, String empName, String deptName) {
        this.address = address;
        this.code = code;
        this.contactPerson = contactPerson;
        this.defCode = defCode;
        this.email = email;
        this.employeeCode = employeeCode;
        this.fax = fax;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.taxCode = taxCode;
        this.trademark = trademark;
        this.id = id;
        this.empId = empId;
        this.deptId = deptId;
        this.empName = empName;
        this.deptName = deptName;
    }

    @Override
    public ClientDto clone(){
        return new ClientDto(address, code, contactPerson, defCode, email, employeeCode, fax, name,
                phone, status, taxCode, trademark, id, empId, deptId, empName, deptName);
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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
