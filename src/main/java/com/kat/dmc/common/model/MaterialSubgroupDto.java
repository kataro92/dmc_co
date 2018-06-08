package com.kat.dmc.common.model;

import java.io.Serializable;

public class MaterialSubgroupDto implements Serializable {

    private String code;
    private String materialGroupCode;
    private String name;
    private String valueToSearch;
    private int id;
    private Integer status;

    public MaterialSubgroupDto() {
    }

    public MaterialSubgroupDto(String code, String materialGroupCode, String name, String valueToSearch, int id, Integer status) {
        this.code = code;
        this.materialGroupCode = materialGroupCode;
        this.name = name;
        this.valueToSearch = valueToSearch;
        this.id = id;
        this.status = status;
    }

    @Override
    public MaterialSubgroupDto clone(){
        return new MaterialSubgroupDto(code, materialGroupCode, name, valueToSearch, id, status);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMaterialGroupCode() {
        return materialGroupCode;
    }

    public void setMaterialGroupCode(String materialGroupCode) {
        this.materialGroupCode = materialGroupCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueToSearch() {
        return valueToSearch;
    }

    public void setValueToSearch(String valueToSearch) {
        this.valueToSearch = valueToSearch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
