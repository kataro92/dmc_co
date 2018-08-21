package com.kat.dmc.common.model;

import java.io.Serializable;


/**
 * @Desc: Store material information, which one is one stock and can use for building product
 */
public class MaterialOnStockDto implements Serializable {
    private Integer materialId;
    private Integer materialQuantity;
    private Integer warehouseId;
    private Integer importId;
    private Integer materialPrice;
    private Integer materialGroupId;

    public MaterialOnStockDto() {
    }

    public MaterialOnStockDto(Integer materialId, Integer materialQuantity, Integer warehouseId
            , Integer importId, Integer materialPrice, Integer materialGroupId) {
        this.materialId = materialId;
        this.materialQuantity = materialQuantity;
        this.warehouseId = warehouseId;
        this.importId = importId;
        this.materialPrice = materialPrice;
        this.setMaterialGroupId(materialGroupId);
    }
    @Override
    public MaterialOnStockDto clone(){
        return new MaterialOnStockDto(materialId, materialQuantity, warehouseId, importId, materialPrice, materialGroupId);
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(Integer materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public Integer getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(Integer materialPrice) {
        this.materialPrice = materialPrice;
    }

    public Integer getMaterialGroupId() {
        return materialGroupId;
    }

    public void setMaterialGroupId(Integer materialGroupId) {
        this.materialGroupId = materialGroupId;
    }
}
