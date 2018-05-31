package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "material_receiving_voucher", schema = "public", catalog = "dmco_sys")
public class MaterialReceivingVoucherEntity {
    private String category;
    private String code;
    private String createdBy;
    private Timestamp createdDate;
    private String defCode;
    private String deliveryCode;
    private String documentDate;
    private String editedBy;
    private String infos0Code;
    private Long infos0Price;
    private Integer infos0Quantity;
    private String infos0Store;
    private String infos1Code;
    private Long infos1Price;
    private Integer infos1Quantity;
    private String infos1Store;
    private String infos2Code;
    private Long infos2Price;
    private Integer infos2Quantity;
    private String infos2Store;
    private String infos3Code;
    private Long infos3Price;
    private Integer infos3Quantity;
    private String infos3Store;
    private String infos4Code;
    private Long infos4Price;
    private Integer infos4Quantity;
    private String infos4Store;
    private String infos5Code;
    private Long infos5Price;
    private Integer infos5Quantity;
    private String infos5Store;
    private Timestamp lastModified;
    private Boolean isPublished;
    private Integer status;
    private String supplierCode;
    private String valueToSearch;
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
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
    @Column(name = "edited_by")
    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    @Basic
    @Column(name = "infos_0_code")
    public String getInfos0Code() {
        return infos0Code;
    }

    public void setInfos0Code(String infos0Code) {
        this.infos0Code = infos0Code;
    }

    @Basic
    @Column(name = "infos_0_price")
    public Long getInfos0Price() {
        return infos0Price;
    }

    public void setInfos0Price(Long infos0Price) {
        this.infos0Price = infos0Price;
    }

    @Basic
    @Column(name = "infos_0_quantity")
    public Integer getInfos0Quantity() {
        return infos0Quantity;
    }

    public void setInfos0Quantity(Integer infos0Quantity) {
        this.infos0Quantity = infos0Quantity;
    }

    @Basic
    @Column(name = "infos_0_store")
    public String getInfos0Store() {
        return infos0Store;
    }

    public void setInfos0Store(String infos0Store) {
        this.infos0Store = infos0Store;
    }

    @Basic
    @Column(name = "infos_1_code")
    public String getInfos1Code() {
        return infos1Code;
    }

    public void setInfos1Code(String infos1Code) {
        this.infos1Code = infos1Code;
    }

    @Basic
    @Column(name = "infos_1_price")
    public Long getInfos1Price() {
        return infos1Price;
    }

    public void setInfos1Price(Long infos1Price) {
        this.infos1Price = infos1Price;
    }

    @Basic
    @Column(name = "infos_1_quantity")
    public Integer getInfos1Quantity() {
        return infos1Quantity;
    }

    public void setInfos1Quantity(Integer infos1Quantity) {
        this.infos1Quantity = infos1Quantity;
    }

    @Basic
    @Column(name = "infos_1_store")
    public String getInfos1Store() {
        return infos1Store;
    }

    public void setInfos1Store(String infos1Store) {
        this.infos1Store = infos1Store;
    }

    @Basic
    @Column(name = "infos_2_code")
    public String getInfos2Code() {
        return infos2Code;
    }

    public void setInfos2Code(String infos2Code) {
        this.infos2Code = infos2Code;
    }

    @Basic
    @Column(name = "infos_2_price")
    public Long getInfos2Price() {
        return infos2Price;
    }

    public void setInfos2Price(Long infos2Price) {
        this.infos2Price = infos2Price;
    }

    @Basic
    @Column(name = "infos_2_quantity")
    public Integer getInfos2Quantity() {
        return infos2Quantity;
    }

    public void setInfos2Quantity(Integer infos2Quantity) {
        this.infos2Quantity = infos2Quantity;
    }

    @Basic
    @Column(name = "infos_2_store")
    public String getInfos2Store() {
        return infos2Store;
    }

    public void setInfos2Store(String infos2Store) {
        this.infos2Store = infos2Store;
    }

    @Basic
    @Column(name = "infos_3_code")
    public String getInfos3Code() {
        return infos3Code;
    }

    public void setInfos3Code(String infos3Code) {
        this.infos3Code = infos3Code;
    }

    @Basic
    @Column(name = "infos_3_price")
    public Long getInfos3Price() {
        return infos3Price;
    }

    public void setInfos3Price(Long infos3Price) {
        this.infos3Price = infos3Price;
    }

    @Basic
    @Column(name = "infos_3_quantity")
    public Integer getInfos3Quantity() {
        return infos3Quantity;
    }

    public void setInfos3Quantity(Integer infos3Quantity) {
        this.infos3Quantity = infos3Quantity;
    }

    @Basic
    @Column(name = "infos_3_store")
    public String getInfos3Store() {
        return infos3Store;
    }

    public void setInfos3Store(String infos3Store) {
        this.infos3Store = infos3Store;
    }

    @Basic
    @Column(name = "infos_4_code")
    public String getInfos4Code() {
        return infos4Code;
    }

    public void setInfos4Code(String infos4Code) {
        this.infos4Code = infos4Code;
    }

    @Basic
    @Column(name = "infos_4_price")
    public Long getInfos4Price() {
        return infos4Price;
    }

    public void setInfos4Price(Long infos4Price) {
        this.infos4Price = infos4Price;
    }

    @Basic
    @Column(name = "infos_4_quantity")
    public Integer getInfos4Quantity() {
        return infos4Quantity;
    }

    public void setInfos4Quantity(Integer infos4Quantity) {
        this.infos4Quantity = infos4Quantity;
    }

    @Basic
    @Column(name = "infos_4_store")
    public String getInfos4Store() {
        return infos4Store;
    }

    public void setInfos4Store(String infos4Store) {
        this.infos4Store = infos4Store;
    }

    @Basic
    @Column(name = "infos_5_code")
    public String getInfos5Code() {
        return infos5Code;
    }

    public void setInfos5Code(String infos5Code) {
        this.infos5Code = infos5Code;
    }

    @Basic
    @Column(name = "infos_5_price")
    public Long getInfos5Price() {
        return infos5Price;
    }

    public void setInfos5Price(Long infos5Price) {
        this.infos5Price = infos5Price;
    }

    @Basic
    @Column(name = "infos_5_quantity")
    public Integer getInfos5Quantity() {
        return infos5Quantity;
    }

    public void setInfos5Quantity(Integer infos5Quantity) {
        this.infos5Quantity = infos5Quantity;
    }

    @Basic
    @Column(name = "infos_5_store")
    public String getInfos5Store() {
        return infos5Store;
    }

    public void setInfos5Store(String infos5Store) {
        this.infos5Store = infos5Store;
    }

    @Basic
    @Column(name = "last_modified")
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
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

    @Basic
    @Column(name = "value_to_search")
    public String getValueToSearch() {
        return valueToSearch;
    }

    public void setValueToSearch(String valueToSearch) {
        this.valueToSearch = valueToSearch;
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
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (defCode != null ? !defCode.equals(that.defCode) : that.defCode != null) return false;
        if (deliveryCode != null ? !deliveryCode.equals(that.deliveryCode) : that.deliveryCode != null) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (editedBy != null ? !editedBy.equals(that.editedBy) : that.editedBy != null) return false;
        if (infos0Code != null ? !infos0Code.equals(that.infos0Code) : that.infos0Code != null) return false;
        if (infos0Price != null ? !infos0Price.equals(that.infos0Price) : that.infos0Price != null) return false;
        if (infos0Quantity != null ? !infos0Quantity.equals(that.infos0Quantity) : that.infos0Quantity != null)
            return false;
        if (infos0Store != null ? !infos0Store.equals(that.infos0Store) : that.infos0Store != null) return false;
        if (infos1Code != null ? !infos1Code.equals(that.infos1Code) : that.infos1Code != null) return false;
        if (infos1Price != null ? !infos1Price.equals(that.infos1Price) : that.infos1Price != null) return false;
        if (infos1Quantity != null ? !infos1Quantity.equals(that.infos1Quantity) : that.infos1Quantity != null)
            return false;
        if (infos1Store != null ? !infos1Store.equals(that.infos1Store) : that.infos1Store != null) return false;
        if (infos2Code != null ? !infos2Code.equals(that.infos2Code) : that.infos2Code != null) return false;
        if (infos2Price != null ? !infos2Price.equals(that.infos2Price) : that.infos2Price != null) return false;
        if (infos2Quantity != null ? !infos2Quantity.equals(that.infos2Quantity) : that.infos2Quantity != null)
            return false;
        if (infos2Store != null ? !infos2Store.equals(that.infos2Store) : that.infos2Store != null) return false;
        if (infos3Code != null ? !infos3Code.equals(that.infos3Code) : that.infos3Code != null) return false;
        if (infos3Price != null ? !infos3Price.equals(that.infos3Price) : that.infos3Price != null) return false;
        if (infos3Quantity != null ? !infos3Quantity.equals(that.infos3Quantity) : that.infos3Quantity != null)
            return false;
        if (infos3Store != null ? !infos3Store.equals(that.infos3Store) : that.infos3Store != null) return false;
        if (infos4Code != null ? !infos4Code.equals(that.infos4Code) : that.infos4Code != null) return false;
        if (infos4Price != null ? !infos4Price.equals(that.infos4Price) : that.infos4Price != null) return false;
        if (infos4Quantity != null ? !infos4Quantity.equals(that.infos4Quantity) : that.infos4Quantity != null)
            return false;
        if (infos4Store != null ? !infos4Store.equals(that.infos4Store) : that.infos4Store != null) return false;
        if (infos5Code != null ? !infos5Code.equals(that.infos5Code) : that.infos5Code != null) return false;
        if (infos5Price != null ? !infos5Price.equals(that.infos5Price) : that.infos5Price != null) return false;
        if (infos5Quantity != null ? !infos5Quantity.equals(that.infos5Quantity) : that.infos5Quantity != null)
            return false;
        if (infos5Store != null ? !infos5Store.equals(that.infos5Store) : that.infos5Store != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (supplierCode != null ? !supplierCode.equals(that.supplierCode) : that.supplierCode != null) return false;
        if (valueToSearch != null ? !valueToSearch.equals(that.valueToSearch) : that.valueToSearch != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (defCode != null ? defCode.hashCode() : 0);
        result = 31 * result + (deliveryCode != null ? deliveryCode.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        result = 31 * result + (editedBy != null ? editedBy.hashCode() : 0);
        result = 31 * result + (infos0Code != null ? infos0Code.hashCode() : 0);
        result = 31 * result + (infos0Price != null ? infos0Price.hashCode() : 0);
        result = 31 * result + (infos0Quantity != null ? infos0Quantity.hashCode() : 0);
        result = 31 * result + (infos0Store != null ? infos0Store.hashCode() : 0);
        result = 31 * result + (infos1Code != null ? infos1Code.hashCode() : 0);
        result = 31 * result + (infos1Price != null ? infos1Price.hashCode() : 0);
        result = 31 * result + (infos1Quantity != null ? infos1Quantity.hashCode() : 0);
        result = 31 * result + (infos1Store != null ? infos1Store.hashCode() : 0);
        result = 31 * result + (infos2Code != null ? infos2Code.hashCode() : 0);
        result = 31 * result + (infos2Price != null ? infos2Price.hashCode() : 0);
        result = 31 * result + (infos2Quantity != null ? infos2Quantity.hashCode() : 0);
        result = 31 * result + (infos2Store != null ? infos2Store.hashCode() : 0);
        result = 31 * result + (infos3Code != null ? infos3Code.hashCode() : 0);
        result = 31 * result + (infos3Price != null ? infos3Price.hashCode() : 0);
        result = 31 * result + (infos3Quantity != null ? infos3Quantity.hashCode() : 0);
        result = 31 * result + (infos3Store != null ? infos3Store.hashCode() : 0);
        result = 31 * result + (infos4Code != null ? infos4Code.hashCode() : 0);
        result = 31 * result + (infos4Price != null ? infos4Price.hashCode() : 0);
        result = 31 * result + (infos4Quantity != null ? infos4Quantity.hashCode() : 0);
        result = 31 * result + (infos4Store != null ? infos4Store.hashCode() : 0);
        result = 31 * result + (infos5Code != null ? infos5Code.hashCode() : 0);
        result = 31 * result + (infos5Price != null ? infos5Price.hashCode() : 0);
        result = 31 * result + (infos5Quantity != null ? infos5Quantity.hashCode() : 0);
        result = 31 * result + (infos5Store != null ? infos5Store.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (supplierCode != null ? supplierCode.hashCode() : 0);
        result = 31 * result + (valueToSearch != null ? valueToSearch.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
