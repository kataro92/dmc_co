package com.kat.dmc.common.dto;

import java.io.Serializable;

public class ProductGroupDto implements Serializable {

    private String code;
    private String name;
    private Integer status;
    private int id;

    public ProductGroupDto() {
    }

    public ProductGroupDto(String code, String name, Integer status, int id) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.id = id;
    }

    @Override
    public ProductGroupDto clone(){
        return new ProductGroupDto(code, name, status, id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
