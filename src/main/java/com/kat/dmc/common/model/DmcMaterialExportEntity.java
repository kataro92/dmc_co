package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_material_export", schema = "public", catalog = "dmco_sys")
public class DmcMaterialExportEntity {
    private int id;
    private String code;
    private Integer categoryId;
    private Timestamp exportDate;
    private int supplierId;
    private int status;
    private Integer exportFromId;
    private Integer warehouseId;
    private String reson;
    private Long total;
    private Integer exportFrom;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "export_date")
    public Timestamp getExportDate() {
        return exportDate;
    }

    public void setExportDate(Timestamp exportDate) {
        this.exportDate = exportDate;
    }

    @Basic
    @Column(name = "supplier_id")
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "export_from_id")
    public Integer getExportFromId() {
        return exportFromId;
    }

    public void setExportFromId(Integer exportFromId) {
        this.exportFromId = exportFromId;
    }

    @Basic
    @Column(name = "warehouse_id")
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Basic
    @Column(name = "reson")
    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    @Basic
    @Column(name = "total")
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Basic
    @Column(name = "export_from")
    public Integer getExportFrom() {
        return exportFrom;
    }

    public void setExportFrom(Integer exportFrom) {
        this.exportFrom = exportFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcMaterialExportEntity that = (DmcMaterialExportEntity) o;

        if (id != that.id) return false;
        if (supplierId != that.supplierId) return false;
        if (status != that.status) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (exportDate != null ? !exportDate.equals(that.exportDate) : that.exportDate != null) return false;
        if (exportFromId != null ? !exportFromId.equals(that.exportFromId) : that.exportFromId != null) return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;
        if (reson != null ? !reson.equals(that.reson) : that.reson != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (exportFrom != null ? !exportFrom.equals(that.exportFrom) : that.exportFrom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (exportDate != null ? exportDate.hashCode() : 0);
        result = 31 * result + supplierId;
        result = 31 * result + status;
        result = 31 * result + (exportFromId != null ? exportFromId.hashCode() : 0);
        result = 31 * result + (warehouseId != null ? warehouseId.hashCode() : 0);
        result = 31 * result + (reson != null ? reson.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (exportFrom != null ? exportFrom.hashCode() : 0);
        return result;
    }
}
