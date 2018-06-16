package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "material_delivery_voucher", schema = "public")
public class MaterialDeliveryVoucherEntity {
    private String code;
    private String createdBy;
    private Timestamp createdDate;
    private String customerCode;
    private String defCode;
    private String deliveryType;
    private String documentDate;
    private String infos0Code;
    private Long infos0Price;
    private Integer infos0Quantity;
    private String infos0ReceivingCode;
    private String infos0Store;
    private String infos1Code;
    private Long infos1Price;
    private Integer infos1Quantity;
    private String infos1ReceivingCode;
    private String infos1Store;
    private String invoiceCode;
    private Timestamp lastModified;
    private String otherCustomerCode;
    private String otherType;
    private Boolean isPublished;
    private String reason;
    private Integer status;
    private String supplierCode;
    private String valueToSearch;
    private int id;

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
    @Column(name = "customer_code")
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
    @Column(name = "delivery_type")
    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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
    @Column(name = "infos_0_receiving_code")
    public String getInfos0ReceivingCode() {
        return infos0ReceivingCode;
    }

    public void setInfos0ReceivingCode(String infos0ReceivingCode) {
        this.infos0ReceivingCode = infos0ReceivingCode;
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
    @Column(name = "infos_1_receiving_code")
    public String getInfos1ReceivingCode() {
        return infos1ReceivingCode;
    }

    public void setInfos1ReceivingCode(String infos1ReceivingCode) {
        this.infos1ReceivingCode = infos1ReceivingCode;
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
    @Column(name = "invoice_code")
    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
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
    @Column(name = "other_customer_code")
    public String getOtherCustomerCode() {
        return otherCustomerCode;
    }

    public void setOtherCustomerCode(String otherCustomerCode) {
        this.otherCustomerCode = otherCustomerCode;
    }

    @Basic
    @Column(name = "other_type")
    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
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
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

        MaterialDeliveryVoucherEntity that = (MaterialDeliveryVoucherEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (customerCode != null ? !customerCode.equals(that.customerCode) : that.customerCode != null) return false;
        if (defCode != null ? !defCode.equals(that.defCode) : that.defCode != null) return false;
        if (deliveryType != null ? !deliveryType.equals(that.deliveryType) : that.deliveryType != null) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (infos0Code != null ? !infos0Code.equals(that.infos0Code) : that.infos0Code != null) return false;
        if (infos0Price != null ? !infos0Price.equals(that.infos0Price) : that.infos0Price != null) return false;
        if (infos0Quantity != null ? !infos0Quantity.equals(that.infos0Quantity) : that.infos0Quantity != null)
            return false;
        if (infos0ReceivingCode != null ? !infos0ReceivingCode.equals(that.infos0ReceivingCode) : that.infos0ReceivingCode != null)
            return false;
        if (infos0Store != null ? !infos0Store.equals(that.infos0Store) : that.infos0Store != null) return false;
        if (infos1Code != null ? !infos1Code.equals(that.infos1Code) : that.infos1Code != null) return false;
        if (infos1Price != null ? !infos1Price.equals(that.infos1Price) : that.infos1Price != null) return false;
        if (infos1Quantity != null ? !infos1Quantity.equals(that.infos1Quantity) : that.infos1Quantity != null)
            return false;
        if (infos1ReceivingCode != null ? !infos1ReceivingCode.equals(that.infos1ReceivingCode) : that.infos1ReceivingCode != null)
            return false;
        if (infos1Store != null ? !infos1Store.equals(that.infos1Store) : that.infos1Store != null) return false;
        if (invoiceCode != null ? !invoiceCode.equals(that.invoiceCode) : that.invoiceCode != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (otherCustomerCode != null ? !otherCustomerCode.equals(that.otherCustomerCode) : that.otherCustomerCode != null)
            return false;
        if (otherType != null ? !otherType.equals(that.otherType) : that.otherType != null) return false;
        if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (supplierCode != null ? !supplierCode.equals(that.supplierCode) : that.supplierCode != null) return false;
        if (valueToSearch != null ? !valueToSearch.equals(that.valueToSearch) : that.valueToSearch != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        result = 31 * result + (defCode != null ? defCode.hashCode() : 0);
        result = 31 * result + (deliveryType != null ? deliveryType.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        result = 31 * result + (infos0Code != null ? infos0Code.hashCode() : 0);
        result = 31 * result + (infos0Price != null ? infos0Price.hashCode() : 0);
        result = 31 * result + (infos0Quantity != null ? infos0Quantity.hashCode() : 0);
        result = 31 * result + (infos0ReceivingCode != null ? infos0ReceivingCode.hashCode() : 0);
        result = 31 * result + (infos0Store != null ? infos0Store.hashCode() : 0);
        result = 31 * result + (infos1Code != null ? infos1Code.hashCode() : 0);
        result = 31 * result + (infos1Price != null ? infos1Price.hashCode() : 0);
        result = 31 * result + (infos1Quantity != null ? infos1Quantity.hashCode() : 0);
        result = 31 * result + (infos1ReceivingCode != null ? infos1ReceivingCode.hashCode() : 0);
        result = 31 * result + (infos1Store != null ? infos1Store.hashCode() : 0);
        result = 31 * result + (invoiceCode != null ? invoiceCode.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (otherCustomerCode != null ? otherCustomerCode.hashCode() : 0);
        result = 31 * result + (otherType != null ? otherType.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (supplierCode != null ? supplierCode.hashCode() : 0);
        result = 31 * result + (valueToSearch != null ? valueToSearch.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
