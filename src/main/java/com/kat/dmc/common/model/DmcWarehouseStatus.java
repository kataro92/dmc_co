package com.kat.dmc.common.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
//@Table(name = "dmc_warehouse_status", schema = "public", catalog = "dmco_sys")
@NamedNativeQueries(value = {
        @NamedNativeQuery(
                name = "DmcWarehouseStatus.findAllBySearchReq",
                query = "SELECT * FROM dmc_warehouse_status " +
                        "WHERE (warehouse_id = :warehouse_id OR :warehouse_id = 0) " +
                        " AND (cast(process_date as int) >= cast(:from_date as int) OR :from_date = '0')" +
                        " AND (cast(process_date as int) <= cast(:to_date as int) OR :to_date = '0')" +
                        " ORDER BY category_id, group_id, subgroup_id, material_id, quantity",
                resultClass = DmcWarehouseStatus.class),
        @NamedNativeQuery(
                name = "DmcWarehouseStatus.findImportBySearchReq",
                query = "SELECT * FROM dmc_warehouse_status " +
                        "WHERE (warehouse_id = :warehouse_id OR :warehouse_id = 0) " +
                        " AND (cast(process_date as int) >= cast(:from_date as int) OR :from_date = '0')" +
                        " AND (cast(process_date as int) <= cast(:to_date as int) OR :to_date = '0')" +
                        " AND quantity > 0" +
                        " ORDER BY category_id, group_id, subgroup_id, material_id, quantity",
                resultClass = DmcWarehouseStatus.class),
        @NamedNativeQuery(
                name = "DmcWarehouseStatus.findExportBySearchReq",
                query = "SELECT * FROM dmc_warehouse_status " +
                        "WHERE (warehouse_id = :warehouse_id OR :warehouse_id = 0) " +
                        " AND (cast(process_date as int) >= cast(:from_date as int) OR :from_date = '0')" +
                        " AND (cast(process_date as int) <= cast(:to_date as int) OR :to_date = '0')" +
                        " AND quantity < 0" +
                        " ORDER BY category_id, group_id, subgroup_id, material_id, quantity",
                resultClass = DmcWarehouseStatus.class),
        @NamedNativeQuery(
                name = "DmcWarehouseStatus.findTempImportBySearchReq",
                query = "SELECT * FROM dmc_warehouse_status " +
                        "WHERE (warehouse_id = :warehouse_id OR :warehouse_id = 0) " +
                        " AND (cast(process_date as int) >= cast(:from_date as int) OR :from_date = '0')" +
                        " AND (cast(process_date as int) <= cast(:to_date as int) OR :to_date = '0')" +
                        " AND quantity = 0" +
                        " ORDER BY category_id, group_id, subgroup_id, material_id, quantity",
                resultClass = DmcWarehouseStatus.class),
            @NamedNativeQuery(
                name = "DmcWarehouseStatus.findDailyStatus",
                query = "SELECT :warehouse_id AS warehouse_id, :process_date AS process_date, 'A' \"type\"" +
                        ", 0 material_id" +
                        ", 0 category_id" +
                        ", '' \"name\"" +
                        ", 0 group_id" +
                        ", '' group_name" +
                        ", 0 subgroup_id" +
                        ", '' subgroup_name" +
                        ", sum(quantity) quantity, sum(price) price FROM dmc_warehouse_status " +
                        "WHERE warehouse_id = :warehouse_id " +
                        "UNION ALL " +
                        "SELECT :warehouse_id AS warehouse_id, :process_date AS process_date, 'B' \"type\"" +
                        ", 0 material_id" +
                        ", 0 category_id" +
                        ", '' \"name\"" +
                        ", 0 group_id" +
                        ", '' group_name" +
                        ", 0 subgroup_id" +
                        ", '' subgroup_name" +
                        ", sum(quantity) quantity, sum(price) price FROM dmc_warehouse_status  " +
                        "WHERE quantity > 0 " +
                        "AND warehouse_id = :warehouse_id " +
                        "AND process_date = :process_date " +
                        "UNION ALL " +
                        "SELECT :warehouse_id AS warehouse_id, :process_date AS process_date, 'C' \"type\"" +
                        ", 0 material_id" +
                        ", 0 category_id" +
                        ", '' \"name\"" +
                        ", 0 group_id" +
                        ", '' group_name" +
                        ", 0 subgroup_id" +
                        ", '' subgroup_name" +
                        ", sum(quantity) quantity, sum(price) price FROM dmc_warehouse_status  " +
                        "WHERE quantity < 0 " +
                        "AND warehouse_id = :warehouse_id " +
                        "AND process_date = :process_date " +
                        "ORDER BY warehouse_id",
                resultClass = DmcWarehouseStatus.class)
}
        )
public class DmcWarehouseStatus implements Serializable {
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

    @Id
    @Basic
    @Column(name = "warehouse_id")
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Id
    @Basic
    @Column(name = "process_date")
    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Id
    @Basic
    @Column(name = "material_id")
    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "subgroup_id")
    public Integer getSubgroupId() {
        return subgroupId;
    }

    public void setSubgroupId(Integer subgroupId) {
        this.subgroupId = subgroupId;
    }

    @Basic
    @Column(name = "subgroup_name")
    public String getSubgroupName() {
        return subgroupName;
    }

    public void setSubgroupName(String subgroupName) {
        this.subgroupName = subgroupName;
    }

    @Basic
    @Column(name = "quantity")
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Id
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcWarehouseStatus that = (DmcWarehouseStatus) o;

        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;
        if (processDate != null ? !processDate.equals(that.processDate) : that.processDate != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (materialId != null ? !materialId.equals(that.materialId) : that.materialId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (subgroupId != null ? !subgroupId.equals(that.subgroupId) : that.subgroupId != null) return false;
        if (subgroupName != null ? !subgroupName.equals(that.subgroupName) : that.subgroupName != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = warehouseId != null ? warehouseId.hashCode() : 0;
        result = 31 * result + (processDate != null ? processDate.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (materialId != null ? materialId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (subgroupId != null ? subgroupId.hashCode() : 0);
        result = 31 * result + (subgroupName != null ? subgroupName.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
