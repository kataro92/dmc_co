package com.kat.dmc.common.dto;

import com.kat.dmc.common.util.DateUtil;

import java.io.Serializable;

public class WarehouseStatusDto implements Serializable {
    private Integer idx;
    private Integer warehouseId;
    private String processDate;
    private Integer categoryId;
    private Integer materialId;
    private String name;
    private Integer groupId;
    private String groupName;
    private Integer subgroupId;
    private String subgroupName;
    private Long quantity;
    private Long price;
    private String type;

    public WarehouseStatusDto() {
    }

    public WarehouseStatusDto(Integer idx, Integer warehouseId, String processDate, Integer
            categoryId, Integer materialId, String name, Integer groupId,
                              String groupName, Integer subgroupId, String subgroupName,
                              Long quantity, Long price, String type) {
        this.warehouseId = warehouseId;
        this.processDate = processDate;
        this.categoryId = categoryId;
        this.materialId = materialId;
        this.name = name;
        this.groupId = groupId;
        this.groupName = groupName;
        this.subgroupId = subgroupId;
        this.subgroupName = subgroupName;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.idx = idx;
    }

    @Override
    public WarehouseStatusDto clone(){
        return new WarehouseStatusDto(idx, warehouseId, processDate, categoryId, materialId, name, groupId, groupName, subgroupId, subgroupName, quantity, price, type);
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getProcessDate() {
        return processDate;
    }
    public String getStrProcessDate() {
        return DateUtil.YYYYMMDD2DDMMYYYY(processDate);
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getSubgroupId() {
        return subgroupId;
    }

    public void setSubgroupId(Integer subgroupId) {
        this.subgroupId = subgroupId;
    }

    public String getSubgroupName() {
        return subgroupName;
    }

    public void setSubgroupName(String subgroupName) {
        this.subgroupName = subgroupName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}
