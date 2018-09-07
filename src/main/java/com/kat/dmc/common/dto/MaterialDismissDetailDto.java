package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.Date;

public class MaterialDismissDetailDto implements Serializable {
    private int id;
    private int materialDismissId;
    private String code;
    private Integer price;
    private Integer quantity;
    private Long total;
    private Integer materialId;
    private Integer materialGroupId;
    private Date dismissDate;
    private Integer createEmpId;
    private int status;

    public MaterialDismissDetailDto() {
    }

    public MaterialDismissDetailDto(int id, int materialDismissId, String code, Integer price, Integer quantity, Long total, Integer materialId, Integer materialGroupId, Date dismissDate, Integer createEmpId, int status) {
        this.id = id;
        this.materialDismissId = materialDismissId;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.materialId = materialId;
        this.materialGroupId = materialGroupId;
        this.dismissDate = dismissDate;
        this.createEmpId = createEmpId;
        this.status = status;
    }

    @Override
    public MaterialDismissDetailDto clone(){
        return new MaterialDismissDetailDto(id, materialDismissId, code, price, quantity, total, materialId, materialGroupId, dismissDate, createEmpId, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterialDismissId() {
        return materialDismissId;
    }

    public void setMaterialDismissId(int materialDismissId) {
        this.materialDismissId = materialDismissId;
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

    public Date getDismissDate() {
        return dismissDate;
    }

    public void setDismissDate(Date dismissDate) {
        this.dismissDate = dismissDate;
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
