package com.kat.dmc.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuildingMaterialDto implements Serializable {
    private int id;
    private Integer productId;
    private Integer materialId;
    private Integer materialType;
    private Integer status;
    private Integer quantity;
    private Integer price;
    private Long total;
    private List<Integer> importId;
    private Date usedDate;
    private String materialCode;
    private String materialName;
    private String materialUnit;
    private Integer buildingImportId;

    public BuildingMaterialDto() {
        importId = new ArrayList<>();
        importId.add(0);
    }

    public BuildingMaterialDto(int id, Integer productId, Integer materialId, Integer materialType,
                               Integer status, Integer quantity, Integer price, Long total, List<Integer> importId,
                               Date usedDate, String materialCode, String materialName, String materialUnit, Integer buildingImportId) {
        this.id = id;
        this.productId = productId;
        this.materialId = materialId;
        this.materialType = materialType;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.importId = importId;
        this.usedDate = usedDate;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialUnit = materialUnit;
        this.buildingImportId = buildingImportId;
    }

    @Override
    public BuildingMaterialDto clone(){
        return new BuildingMaterialDto(id, productId, materialId, materialType, status, quantity,
                price, total, importId, usedDate, materialCode, materialName, materialUnit, buildingImportId);
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

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Integer> getImportId() {
        return importId;
    }

    public void setImportId(List<Integer> importId) {
        this.importId = importId;
    }

    public Date getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(Date usedDate) {
        this.usedDate = usedDate;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public Integer getBuildingImportId() {
        return buildingImportId;
    }

    public void setBuildingImportId(Integer buildingImportId) {
        this.buildingImportId = buildingImportId;
    }
}
