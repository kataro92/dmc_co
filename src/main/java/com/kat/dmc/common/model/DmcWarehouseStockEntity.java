package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_warehouse_stock", schema = "public", catalog = "dmco_sys")
public class DmcWarehouseStockEntity {
    private int id;
    private int warehouseId;
    private int materialId;
    private int quantity;
    private int total;
    private Timestamp dateCheck;
    private String type;
    private Integer importId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "warehouse_id")
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Basic
    @Column(name = "material_id")
    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "total")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Basic
    @Column(name = "date_check")
    public Timestamp getDateCheck() {
        return dateCheck;
    }

    public void setDateCheck(Timestamp dateCheck) {
        this.dateCheck = dateCheck;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "import_id")
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcWarehouseStockEntity that = (DmcWarehouseStockEntity) o;

        if (id != that.id) return false;
        if (warehouseId != that.warehouseId) return false;
        if (materialId != that.materialId) return false;
        if (quantity != that.quantity) return false;
        if (total != that.total) return false;
        if (dateCheck != null ? !dateCheck.equals(that.dateCheck) : that.dateCheck != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (importId != null ? !importId.equals(that.importId) : that.importId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + warehouseId;
        result = 31 * result + materialId;
        result = 31 * result + quantity;
        result = 31 * result + total;
        result = 31 * result + (dateCheck != null ? dateCheck.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (importId != null ? importId.hashCode() : 0);
        return result;
    }
}
