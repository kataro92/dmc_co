package com.kat.dmc.common.model;

import java.io.Serializable;
import java.util.Date;

public class MaterialImportDetailDto implements Serializable {
    private int id;
    private int materialImportId;
    private String code;
    private Integer price;
    private Integer quantity;
    private Long total;
    private Integer materialId;
    private Integer materialGroupId;
    private Date importDate;
    private Integer createEmpId;
    private int status;
    private String unit;

    public MaterialImportDetailDto() {
    }

    public MaterialImportDetailDto(int id, int materialImportId, String code, Integer price,
                                   Integer quantity, Long total, Integer materialId, Integer materialGroupId,
                                   Date importDate, Integer createEmpId, int status, String unit) {
        this.id = id;
        this.materialImportId = materialImportId;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.materialId = materialId;
        this.materialGroupId = materialGroupId;
        this.importDate = importDate;
        this.createEmpId = createEmpId;
        this.status = status;
        this.unit = unit;
    }

    @Override
    public MaterialImportDetailDto clone(){
        return new MaterialImportDetailDto(id, materialImportId, code, price, quantity, total, materialId,
                materialGroupId, importDate, createEmpId, status, unit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterialImportId() {
        return materialImportId;
    }

    public void setMaterialImportId(int materialImportId) {
        this.materialImportId = materialImportId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getMaterialGroupId() {
        return materialGroupId;
    }

    public void setMaterialGroupId(Integer materialGroupId) {
        this.materialGroupId = materialGroupId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Integer getCreateEmpId() {
        return createEmpId;
    }

    public void setCreateEmpId(Integer createEmpId) {
        this.createEmpId = createEmpId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
