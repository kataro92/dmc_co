package com.kat.dmc.common.dto;

import java.io.Serializable;

public class WarehouseDailyStatusDto implements Serializable {
    private Long warehouseId;
    private Long quantityOnStock;
    private Long priceOnStock;
    private Long quantityImportDay;
    private Long priceImportDay;
    private Long quantityExportDay;
    private Long priceExportDay;

    public WarehouseDailyStatusDto() {
    }

    public WarehouseDailyStatusDto(Long warehouseId, Long quantityOnStock, Long priceOnStock, Long quantityImportDay, Long priceImportDay, Long quantityExportDay, Long priceExportDay) {
        this.warehouseId = warehouseId;
        this.quantityOnStock = quantityOnStock;
        this.priceOnStock = priceOnStock;
        this.quantityImportDay = quantityImportDay;
        this.priceImportDay = priceImportDay;
        this.quantityExportDay = quantityExportDay;
        this.priceExportDay = priceExportDay;
    }
    
    @Override
    public WarehouseDailyStatusDto clone(){
        return new WarehouseDailyStatusDto(warehouseId, quantityOnStock, priceOnStock, 
                quantityImportDay, priceImportDay, quantityExportDay, priceExportDay);
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getQuantityOnStock() {
        return quantityOnStock;
    }

    public void setQuantityOnStock(Long quantityOnStock) {
        this.quantityOnStock = quantityOnStock;
    }

    public Long getPriceOnStock() {
        return priceOnStock;
    }

    public void setPriceOnStock(Long priceOnStock) {
        this.priceOnStock = priceOnStock;
    }

    public Long getQuantityImportDay() {
        return quantityImportDay;
    }

    public void setQuantityImportDay(Long quantityImportDay) {
        this.quantityImportDay = quantityImportDay;
    }

    public Long getPriceImportDay() {
        return priceImportDay;
    }

    public void setPriceImportDay(Long priceImportDay) {
        this.priceImportDay = priceImportDay;
    }

    public Long getQuantityExportDay() {
        return quantityExportDay;
    }

    public void setQuantityExportDay(Long quantityExportDay) {
        this.quantityExportDay = quantityExportDay;
    }

    public Long getPriceExportDay() {
        return priceExportDay;
    }

    public void setPriceExportDay(Long priceExportDay) {
        this.priceExportDay = priceExportDay;
    }
}
