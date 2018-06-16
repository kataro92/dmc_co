package com.kat.dmc.common.model;

import java.io.Serializable;
import java.util.Date;

public class BuildingProductDto implements Serializable {
    private int id;
    private Integer productId;
    private Integer warehouseId;
    private Date createdDate;
    private Integer siteMoney;
    private Integer price;
    private Integer quantity;
    private Long total;
    private Integer status;
    private Integer code;

    public BuildingProductDto() {
    }

    public BuildingProductDto(int id, Integer productId, Integer warehouseId, Date createdDate, Integer siteMoney, Integer price, Integer quantity, Long total, Integer status, Integer code) {
        this.id = id;
        this.productId = productId;
        this.warehouseId = warehouseId;
        this.createdDate = createdDate;
        this.siteMoney = siteMoney;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
        this.code = code;
    }

    @Override
    public BuildingProductDto clone(){
        return new BuildingProductDto(id, productId, warehouseId, createdDate, siteMoney, price, quantity, total, status, code);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getSiteMoney() {
        return siteMoney;
    }

    public void setSiteMoney(Integer siteMoney) {
        this.siteMoney = siteMoney;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
