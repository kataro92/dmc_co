package com.kat.dmc.common.model;

import java.io.Serializable;

public class MaterialDto implements Serializable {

    private String code;
    private Boolean isCodeFixed;
    private String materialGroupCode;
    private String materialSubgroupCode;
    private String name;
    private String producer;
    private String shortDescription;
    private String sortName;
    private Integer status;
    private String unit;
    private int id;
    private String fullCode;

    public MaterialDto() {
    }

    public MaterialDto(String code, Boolean isCodeFixed, String materialGroupCode,
                       String materialSubgroupCode, String name, String producer,
                       String shortDescription, String sortName, Integer status,
                       String unit, int id, String fullCode) {
        this.code = code;
        this.isCodeFixed = isCodeFixed;
        this.materialGroupCode = materialGroupCode;
        this.materialSubgroupCode = materialSubgroupCode;
        this.name = name;
        this.producer = producer;
        this.shortDescription = shortDescription;
        this.sortName = sortName;
        this.status = status;
        this.unit = unit;
        this.id = id;
        this.fullCode = fullCode;
    }

    @Override
    public MaterialDto clone(){
        return new MaterialDto(code, isCodeFixed, materialGroupCode, materialSubgroupCode, name, producer, shortDescription, sortName, status, unit, id, fullCode);
    }

    public String getFullCode() {
        return fullCode;
    }

    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getCodeFixed() {
        return isCodeFixed;
    }

    public void setCodeFixed(Boolean codeFixed) {
        isCodeFixed = codeFixed;
    }

    public String getMaterialGroupCode() {
        return materialGroupCode;
    }

    public void setMaterialGroupCode(String materialGroupCode) {
        this.materialGroupCode = materialGroupCode;
    }

    public String getMaterialSubgroupCode() {
        return materialSubgroupCode;
    }

    public void setMaterialSubgroupCode(String materialSubgroupCode) {
        this.materialSubgroupCode = materialSubgroupCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
