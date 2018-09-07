package com.kat.dmc.common.dto;

import java.io.Serializable;

public class MaterialGroupDto implements Serializable {
    private String code;
    private String description;
    private String name;
    private Integer status;
    private int id;

    public MaterialGroupDto() {
    }

    public MaterialGroupDto(String code, String description, String name, Integer status, int id) {
        this.code = code;
        this.description = description;
        this.name = name;
        this.status = status;
        this.id = id;
    }
    @Override
    public MaterialGroupDto clone(){
        return new MaterialGroupDto(code, description, name, status, id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
