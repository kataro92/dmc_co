package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_material_import_detail", schema = "public")
public class DmcMaterialImportDetailEntity {
    private int id;
    private int materialImportId;
    private String code;
    private Integer price;
    private Integer quantity;
    private Long total;
    private Integer materialId;
    private Integer materialGroupId;
    private Integer productId;
    private Integer productGroupId;
    private Timestamp importDate;
//    private Integer createEmpId;
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
    @Column(name = "material_import_id")
    public int getMaterialImportId() {
        return materialImportId;
    }

    public void setMaterialImportId(int materialImportId) {
        this.materialImportId = materialImportId;
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
    @Column(name = "import_date")
    public Timestamp getImportDate() {
        return importDate;
    }

    public void setImportDate(Timestamp importDate) {
        this.importDate = importDate;
    }

//    @Basic
//    @Column(name = "create_emp_id")
//    public Integer getCreateEmpId() {
//        return createEmpId;
//    }
//
//    public void setCreateEmpId(Integer createEmpId) {
//        this.createEmpId = createEmpId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcMaterialImportDetailEntity that = (DmcMaterialImportDetailEntity) o;

        if (status != that.status) return false;
        if (id != that.id) return false;
        if (materialImportId != that.materialImportId) return false;
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
        if (importDate != null ? !importDate.equals(that.importDate) : that.importDate != null) return false;
//        if (createEmpId != null ? !createEmpId.equals(that.createEmpId) : that.createEmpId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + materialImportId;
        result = 31 * result + status;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (materialId != null ? materialId.hashCode() : 0);
        result = 31 * result + (materialGroupId != null ? materialGroupId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productGroupId != null ? productGroupId.hashCode() : 0);
        result = 31 * result + (importDate != null ? importDate.hashCode() : 0);
//        result = 31 * result + (createEmpId != null ? createEmpId.hashCode() : 0);
        return result;
    }
}
