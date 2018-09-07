package com.kat.dmc.common.dto;

import java.io.Serializable;

public class DocumentDto implements Serializable {
    private Integer id;
    private String name;
    private String code;
    private String path;

    public DocumentDto() {
    }

    public DocumentDto(Integer id, String name, String code, String path) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.path = path;
    }

    @Override
    public DocumentDto clone(){
        return new DocumentDto(id,name,code,path);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
