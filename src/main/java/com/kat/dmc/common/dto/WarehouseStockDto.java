package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WarehouseStockDto implements Serializable {

    private int id;
    private int warehouseId;
    private int materialId;
    private int quantity;
    private BigDecimal total;
    private Date dateCheck;
    private String type;
    private Integer importId;

    public WarehouseStockDto() {
    }

    public WarehouseStockDto(int id, int warehouseId, int materialId, int quantity, BigDecimal total,
                             Date dateCheck, String type, Integer importId) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.materialId = materialId;
        this.quantity = quantity;
        this.total = total;
        this.dateCheck = dateCheck;
        this.type = type;
        this.importId = importId;
    }

    @Override
    public WarehouseStockDto clone(){
        return new WarehouseStockDto(id, warehouseId, materialId, quantity, total, dateCheck, type, importId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getDateCheck() {
        return dateCheck;
    }

    public void setDateCheck(Date dateCheck) {
        this.dateCheck = dateCheck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }
}
