package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BuildingProductDto implements Serializable {
    private int id;
    private Integer productId;
    private Integer warehouseId;
    private Date createdDate;
    private Integer siteMoney;
    private Long price;
    private Integer quantity;
    private Long total;
    private Integer status;
    private Integer code;
    private String warehouseName;
    private String productName;
    private Integer productGroupId;
    private List<BuildingMaterialDto> buildingMaterialDtos;
    private String materialClassify;
    public BuildingProductDto() {
    }

    public BuildingProductDto(int id, Integer productId, Integer warehouseId, Date createdDate,
                              Integer siteMoney, Long price, Integer quantity, Long total,
                              Integer status, Integer code, String warehouseName, String productName,
                              List<BuildingMaterialDto> buildingMaterialDtos,
                              Integer productGroupId, String materialClassify) {
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
        this.warehouseName = warehouseName;
        this.productName = productName;
        this.buildingMaterialDtos = buildingMaterialDtos;
        this.productGroupId = productGroupId;
        this.materialClassify = materialClassify;
    }

    @Override
    public BuildingProductDto clone(){
        return new BuildingProductDto(id, productId, warehouseId, createdDate, siteMoney, price,
                quantity, total, status, code, warehouseName, productName,
                buildingMaterialDtos, productGroupId, materialClassify);
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<BuildingMaterialDto> getBuildingMaterialDtos() {
        return buildingMaterialDtos;
    }

    public void setBuildingMaterialDtos(List<BuildingMaterialDto> buildingMaterialDtos) {
        this.buildingMaterialDtos = buildingMaterialDtos;
    }

    public Integer getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Integer productGroupId) {
        this.productGroupId = productGroupId;
    }

    public String getMaterialClassify() {
        return materialClassify;
    }

    public void setMaterialClassify(String materialClassify) {
        this.materialClassify = materialClassify;
    }
}
