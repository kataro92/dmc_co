package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.Date;

public class MaterialExportDetailDto implements Serializable {
    private int id;
    private int materialExportId;
    private String code;
    private Integer price;
    private int quantity;
    private Long total;
    private Integer materialId;
    private Integer materialGroupId;
    private Date exportDate;
    private Integer createEmpId;
    private int status;

    public MaterialExportDetailDto() {
    }

    public MaterialExportDetailDto(int id, int materialExportId, String code, Integer price, int quantity, Long total, Integer materialId, Integer materialGroupId, Date exportDate, Integer createEmpId, int status) {
        this.id = id;
        this.materialExportId = materialExportId;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.materialId = materialId;
        this.materialGroupId = materialGroupId;
        this.exportDate = exportDate;
        this.createEmpId = createEmpId;
        this.status = status;
    }

    @Override
    public MaterialExportDetailDto clone(){
        return new MaterialExportDetailDto(id, materialExportId, code, price, quantity, total, materialId, materialGroupId, exportDate, createEmpId, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterialExportId() {
        return materialExportId;
    }

    public void setMaterialExportId(int materialExportId) {
        this.materialExportId = materialExportId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
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
}
