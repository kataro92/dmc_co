package com.kat.dmc.common.dto;

import java.io.Serializable;

public class ProductSubgroupDto implements Serializable {

    private String code;
    private String name;
    private String productGroupCode;
    private Integer status;
    private int id;

    public ProductSubgroupDto() {
    }

    public ProductSubgroupDto(String code, String name, String productGroupCode, Integer status, int id) {
        this.code = code;
        this.name = name;
        this.productGroupCode = productGroupCode;
        this.status = status;
        this.id = id;
    }
    @Override
    public ProductSubgroupDto clone(){
        return new ProductSubgroupDto(code, name, productGroupCode, status, id);
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

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
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
