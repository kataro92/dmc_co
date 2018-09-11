package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_material_import", schema = "public")
public class DmcMaterialImportEntity {
    private int id;
    private String code;
    private Integer categoryId;
    private Timestamp importDate;
    private int supplierId;
    private int status;
    private Integer importFromId;
    private Integer warehouseId;
    private String reson;
    private Long total;
    private Integer importFrom;
    private Boolean tempImport;

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
    @Column(name = "import_date")
    public Timestamp getImportDate() {
        return importDate;
    }

    public void setImportDate(Timestamp importDate) {
        this.importDate = importDate;
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
    @Column(name = "import_from_id")
    public Integer getImportFromId() {
        return importFromId;
    }

    public void setImportFromId(Integer importFromId) {
        this.importFromId = importFromId;
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
    @Column(name = "import_from")
    public Integer getImportFrom() {
        return importFrom;
    }

    public void setImportFrom(Integer importFrom) {
        this.importFrom = importFrom;
    }

    @Basic
    @Column(name = "temp_import")
    public Boolean getTempImport() {
        return tempImport;
    }

    public void setTempImport(Boolean importFrom) {
        this.tempImport = tempImport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcMaterialImportEntity that = (DmcMaterialImportEntity) o;

        if (id != that.id) return false;
        if (supplierId != that.supplierId) return false;
        if (status != that.status) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (importDate != null ? !importDate.equals(that.importDate) : that.importDate != null) return false;
        if (importFromId != null ? !importFromId.equals(that.importFromId) : that.importFromId != null) return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;
        if (reson != null ? !reson.equals(that.reson) : that.reson != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (importFrom != null ? !importFrom.equals(that.importFrom) : that.importFrom != null) return false;
        if (tempImport != null ? !tempImport.equals(that.tempImport) : that.tempImport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (importDate != null ? importDate.hashCode() : 0);
        result = 31 * result + supplierId;
        result = 31 * result + status;
        result = 31 * result + (importFromId != null ? importFromId.hashCode() : 0);
        result = 31 * result + (warehouseId != null ? warehouseId.hashCode() : 0);
        result = 31 * result + (reson != null ? reson.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (importFrom != null ? importFrom.hashCode() : 0);
        result = 31 * result + (tempImport != null ? tempImport.hashCode() : 0);
        return result;
    }
}
