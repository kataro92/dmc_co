package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_material_dismiss_detail", schema = "public", catalog = "dmco_sys")
public class DmcMaterialDismissDetailEntity {
    private int id;
    private int materialDismissId;
    private String code;
    private Integer price;
    private Integer quantity;
    private Long total;
    private Integer materialId;
    private Integer materialGroupId;
    private Timestamp dismissDate;
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
    @Column(name = "material_dismiss_id")
    public int getMaterialDismissId() {
        return materialDismissId;
    }

    public void setMaterialDismissId(int materialDismissId) {
        this.materialDismissId = materialDismissId;
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
    @Column(name = "dismiss_date")
    public Timestamp getDismissDate() {
        return dismissDate;
    }

    public void setDismissDate(Timestamp dismissDate) {
        this.dismissDate = dismissDate;
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

        DmcMaterialDismissDetailEntity that = (DmcMaterialDismissDetailEntity) o;

        if (status != that.status) return false;
        if (id != that.id) return false;
        if (materialDismissId != that.materialDismissId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (materialId != null ? !materialId.equals(that.materialId) : that.materialId != null) return false;
        if (materialGroupId != null ? !materialGroupId.equals(that.materialGroupId) : that.materialGroupId != null)
            return false;
        if (dismissDate != null ? !dismissDate.equals(that.dismissDate) : that.dismissDate != null) return false;
        if (createEmpId != null ? !createEmpId.equals(that.createEmpId) : that.createEmpId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + materialDismissId;
        result = 31 * result + status;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (materialId != null ? materialId.hashCode() : 0);
        result = 31 * result + (materialGroupId != null ? materialGroupId.hashCode() : 0);
        result = 31 * result + (dismissDate != null ? dismissDate.hashCode() : 0);
        result = 31 * result + (createEmpId != null ? createEmpId.hashCode() : 0);
        return result;
    }
}
