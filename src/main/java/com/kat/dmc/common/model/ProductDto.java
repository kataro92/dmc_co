package com.kat.dmc.common.model;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private String code;
    private String name;
    private String producer;
    private String productGroupCode;
    private String productSubgroupCode;
    private String shortDescription;
    private String sortName;
    private Integer status;
    private int id;
    private String unit;
    private String fullCode;
    private Boolean isCodeFixed;

    public ProductDto() {
    }

    public ProductDto(String code, String name, String producer, String productGroupCode,
                      String productSubgroupCode, String shortDescription, String sortName,
                      Integer status, int id, String unit, String fullCode, Boolean isCodeFixed) {
        this.code = code;
        this.name = name;
        this.producer = producer;
        this.productGroupCode = productGroupCode;
        this.productSubgroupCode = productSubgroupCode;
        this.shortDescription = shortDescription;
        this.sortName = sortName;
        this.status = status;
        this.id = id;
        this.unit = unit;
        this.fullCode = fullCode;
        this.isCodeFixed = isCodeFixed;
    }

    @Override
    public ProductDto clone(){
        return new ProductDto(code, name, producer, productGroupCode, productSubgroupCode,
                shortDescription, sortName, status, id, unit, fullCode, isCodeFixed);
    }

    public Boolean getCodeFixed() {
        return isCodeFixed;
    }

    public void setCodeFixed(Boolean codeFixed) {
        isCodeFixed = codeFixed;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
    }

    public String getProductSubgroupCode() {
        return productSubgroupCode;
    }

    public void setProductSubgroupCode(String productSubgroupCode) {
        this.productSubgroupCode = productSubgroupCode;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
