package com.kat.dmc.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MaterialIETDDto implements Serializable {

    private int id;
    private String code;
    private String name;
    private String unit;
    private String categoryName;
    private Date createDate;
    private String supplierName;
    private String status;
    private Integer importFromName;
    private Integer warehouseName;
    private Integer importFrom;
    private String reson;
    private Long price;
    private Long quantity;
    private Long total;
    private List<MaterialIETDDto> lstDetails;
    private int type;//0: import, 1: export, 2: transfer, 3: dismiss

    public MaterialIETDDto() {
    }

    public MaterialIETDDto(int id, String code, String name, String unit, String categoryName, Date createDate, String supplierName, String status, Integer importFromName, Integer warehouseName, Integer importFrom, String reson, Long price, Long quantity, Long total, List<MaterialIETDDto> lstDetails, int type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.categoryName = categoryName;
        this.createDate = createDate;
        this.supplierName = supplierName;
        this.status = status;
        this.importFromName = importFromName;
        this.warehouseName = warehouseName;
        this.importFrom = importFrom;
        this.reson = reson;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.lstDetails = lstDetails;
        this.type = type;
    }
    public MaterialIETDDto clone(){
        return new MaterialIETDDto(id, code, name, unit, categoryName, createDate, supplierName, status, importFromName, warehouseName, importFrom, reson, price, quantity, total, lstDetails, type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getImportFromName() {
        return importFromName;
    }

    public void setImportFromName(Integer importFromName) {
        this.importFromName = importFromName;
    }

    public Integer getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(Integer warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getImportFrom() {
        return importFrom;
    }

    public void setImportFrom(Integer importFrom) {
        this.importFrom = importFrom;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<MaterialIETDDto> getLstDetails() {
        return lstDetails;
    }

    public void setLstDetails(List<MaterialIETDDto> lstDetails) {
        this.lstDetails = lstDetails;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
