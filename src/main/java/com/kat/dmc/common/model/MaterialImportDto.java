package com.kat.dmc.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MaterialImportDto implements Serializable {

    private int id;
    private String code;
    private int categoryId;
    private Date importDate;
    private int supplierId;
    private int status;
    private List<MaterialImportDetailDto> lstDetails;
    private Integer importFromId;
    private Integer warehouseId;
    private Integer importFrom;
    private String reson;
    private Long total;
    private Integer childQuantity;

    @Override
    public MaterialImportDto clone(){
        return new MaterialImportDto(id, code, categoryId, importDate, supplierId, status, lstDetails,
                importFromId, warehouseId, importFrom, reson, total, childQuantity);
    }

    public MaterialImportDto(int id, String code, int categoryId, Date importDate,
                             int supplierId, int status,
                             List<MaterialImportDetailDto> lstDetails, Integer importFromId,
                             Integer warehouseId, Integer importFrom, String reson, Long total, Integer childQuantity) {
        this.id = id;
        this.code = code;
        this.categoryId = categoryId;
        this.importDate = importDate;
        this.supplierId = supplierId;
        this.status = status;
        this.lstDetails = lstDetails;
        this.importFromId = importFromId;
        this.warehouseId = warehouseId;
        this.importFrom = importFrom;
        this.reson = reson;
        this.total = total;
        this.childQuantity = childQuantity;
    }

    public Integer getChildQuantity() {
        return childQuantity;
    }

    public void setChildQuantity(Integer childQuantity) {
        this.childQuantity = childQuantity;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public MaterialImportDto() {

    }

    public Integer getImportFrom() {
        return importFrom;
    }

    public void setImportFrom(Integer importFrom) {
        this.importFrom = importFrom;
    }

    public Integer getImportFromId() {
        return importFromId;
    }

    public void setImportFromId(Integer importFromId) {
        this.importFromId = importFromId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<MaterialImportDetailDto> getLstDetails() {
        return lstDetails;
    }

    public void setLstDetails(List<MaterialImportDetailDto> lstDetails) {
        this.lstDetails = lstDetails;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MaterialImportDto(List<MaterialImportDetailDto> lstDetails) {
        this.lstDetails = lstDetails;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
