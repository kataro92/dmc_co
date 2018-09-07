package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "material_receiving_voucher", schema = "public")
public class MaterialReceivingVoucherEntity {
    private String category;
    private String code;
    private String defCode;
    private String deliveryCode;
    private String documentDate;
    private Boolean isPublished;
    private Integer status;
    private String supplierCode;
    private int id;

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
    @Column(name = "def_code")
    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    @Basic
    @Column(name = "delivery_code")
    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    @Basic
    @Column(name = "document_date")
    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    @Basic
    @Column(name = "is_published")
    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "supplier_code")
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Id
    @Column(name = "_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialReceivingVoucherEntity that = (MaterialReceivingVoucherEntity) o;

        if (id != that.id) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (defCode != null ? !defCode.equals(that.defCode) : that.defCode != null) return false;
        if (deliveryCode != null ? !deliveryCode.equals(that.deliveryCode) : that.deliveryCode != null) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (supplierCode != null ? !supplierCode.equals(that.supplierCode) : that.supplierCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (defCode != null ? defCode.hashCode() : 0);
        result = 31 * result + (deliveryCode != null ? deliveryCode.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (supplierCode != null ? supplierCode.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
