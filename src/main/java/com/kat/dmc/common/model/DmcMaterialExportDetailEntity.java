package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_material_export_detail", schema = "public")
public class DmcMaterialExportDetailEntity {
    private int id;
    private int materialExportId;
    private String code;
    private Integer price;
    private Integer quantity;
    private Long total;
    private Integer materialId;
    private Integer materialGroupId;
    private Integer productId;
    private Integer productGroupId;
    private Timestamp exportDate;
    private Integer createEmpId;
    private int status;

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "material_export_id")
    public int getMaterialExportId() {
        return materialExportId;
    }

    public void setMaterialExportId(int materialExportId) {
        this.materialExportId = materialExportId;
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
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
    @Column(name = "material_id")
    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "material_group_id")
    public Integer getMaterialGroupId() {
        return materialGroupId;
    }

    public void setMaterialGroupId(Integer materialGroupId) {
        this.materialGroupId = materialGroupId;
    }

    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_group_id")
    public Integer getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(Integer productGroupId) {
        this.productGroupId = productGroupId;
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
    @Column(name = "create_emp_id")
    public Integer getCreateEmpId() {
        return createEmpId;
    }

    public void setCreateEmpId(Integer createEmpId) {
        this.createEmpId = createEmpId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcMaterialExportDetailEntity that = (DmcMaterialExportDetailEntity) o;

        if (status != that.status) return false;
        if (id != that.id) return false;
        if (materialExportId != that.materialExportId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (materialId != null ? !materialId.equals(that.materialId) : that.materialId != null) return false;
        if (materialGroupId != null ? !materialGroupId.equals(that.materialGroupId) : that.materialGroupId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (productGroupId != null ? !productGroupId.equals(that.productGroupId) : that.productGroupId != null)
            return false;
        if (exportDate != null ? !exportDate.equals(that.exportDate) : that.exportDate != null) return false;
        if (createEmpId != null ? !createEmpId.equals(that.createEmpId) : that.createEmpId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + materialExportId;
        result = 31 * result + status;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (materialId != null ? materialId.hashCode() : 0);
        result = 31 * result + (materialGroupId != null ? materialGroupId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productGroupId != null ? productGroupId.hashCode() : 0);
        result = 31 * result + (exportDate != null ? exportDate.hashCode() : 0);
        result = 31 * result + (createEmpId != null ? createEmpId.hashCode() : 0);
        return result;
    }
}
