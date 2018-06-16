package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_material_dismiss", schema = "public")
public class DmcMaterialDismissEntity {
    private int id;
    private String code;
    private Integer categoryId;
    private Timestamp dismissDate;
    private int supplierId;
    private int status;
    private Integer dismissFromId;
    private Integer warehouseId;
    private String reson;
    private Long total;
    private Integer dismissFrom;

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
    @Column(name = "dismiss_date")
    public Timestamp getDismissDate() {
        return dismissDate;
    }

    public void setDismissDate(Timestamp dismissDate) {
        this.dismissDate = dismissDate;
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
    @Column(name = "dismiss_from_id")
    public Integer getDismissFromId() {
        return dismissFromId;
    }

    public void setDismissFromId(Integer dismissFromId) {
        this.dismissFromId = dismissFromId;
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
    @Column(name = "dismiss_from")
    public Integer getDismissFrom() {
        return dismissFrom;
    }

    public void setDismissFrom(Integer dismissFrom) {
        this.dismissFrom = dismissFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcMaterialDismissEntity that = (DmcMaterialDismissEntity) o;

        if (id != that.id) return false;
        if (supplierId != that.supplierId) return false;
        if (status != that.status) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (dismissDate != null ? !dismissDate.equals(that.dismissDate) : that.dismissDate != null) return false;
        if (dismissFromId != null ? !dismissFromId.equals(that.dismissFromId) : that.dismissFromId != null) return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;
        if (reson != null ? !reson.equals(that.reson) : that.reson != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (dismissFrom != null ? !dismissFrom.equals(that.dismissFrom) : that.dismissFrom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (dismissDate != null ? dismissDate.hashCode() : 0);
        result = 31 * result + supplierId;
        result = 31 * result + status;
        result = 31 * result + (dismissFromId != null ? dismissFromId.hashCode() : 0);
        result = 31 * result + (warehouseId != null ? warehouseId.hashCode() : 0);
        result = 31 * result + (reson != null ? reson.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (dismissFrom != null ? dismissFrom.hashCode() : 0);
        return result;
    }
}
