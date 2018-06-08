package com.kat.dmc.common.model;

import java.io.Serializable;

public class SupplierDto implements Serializable {

    private String address;
    private String code;
    private String contactPerson;
    private String defCode;
    private String description;
    private String email;
    private String fax;
    private String name;
    private String phone;
    private Integer status;
    private String taxCode;
    private String trademark;
    private int id;

    public SupplierDto() {
    }

    public SupplierDto(String address, String code, String contactPerson, String defCode, String description, String email, String fax, String name, String phone, Integer status, String taxCode, String trademark, int id) {
        this.address = address;
        this.code = code;
        this.contactPerson = contactPerson;
        this.defCode = defCode;
        this.description = description;
        this.email = email;
        this.fax = fax;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.taxCode = taxCode;
        this.trademark = trademark;
        this.id = id;
    }

    public SupplierDto clone(){
        return new SupplierDto(address, code, contactPerson, defCode, description, email, fax, name, phone, status, taxCode, trademark, id);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
