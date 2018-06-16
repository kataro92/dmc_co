package com.kat.dmc.common.model;

import javax.persistence.*;

@Entity
@Table(name = "dmc_warehouse", schema = "public")
public class DmcWarehouseEntity {
    private int id;
    private String name;
    private boolean isCanImport;
    private boolean isCanExport;
    private Boolean isCanTransfer;
    private Boolean isCanDismiss;
    private String address;
    private int status;
    private String code;
    private Integer categoryId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "is_can_import")
    public boolean isCanImport() {
        return isCanImport;
    }

    public void setCanImport(boolean canImport) {
        isCanImport = canImport;
    }

    @Basic
    @Column(name = "is_can_export")
    public boolean isCanExport() {
        return isCanExport;
    }

    public void setCanExport(boolean canExport) {
        isCanExport = canExport;
    }

    @Basic
    @Column(name = "is_can_transfer")
    public Boolean getCanTransfer() {
        return isCanTransfer;
    }

    public void setCanTransfer(Boolean canTransfer) {
        isCanTransfer = canTransfer;
    }

    @Basic
    @Column(name = "is_can_dismiss")
    public Boolean getCanDismiss() {
        return isCanDismiss;
    }

    public void setCanDismiss(Boolean canDismiss) {
        isCanDismiss = canDismiss;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcWarehouseEntity that = (DmcWarehouseEntity) o;

        if (id != that.id) return false;
        if (isCanImport != that.isCanImport) return false;
        if (isCanExport != that.isCanExport) return false;
        if (status != that.status) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (isCanTransfer != null ? !isCanTransfer.equals(that.isCanTransfer) : that.isCanTransfer != null)
            return false;
        if (isCanDismiss != null ? !isCanDismiss.equals(that.isCanDismiss) : that.isCanDismiss != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isCanImport ? 1 : 0);
        result = 31 * result + (isCanExport ? 1 : 0);
        result = 31 * result + (isCanTransfer != null ? isCanTransfer.hashCode() : 0);
        result = 31 * result + (isCanDismiss != null ? isCanDismiss.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }
}
