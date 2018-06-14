package com.kat.dmc.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MaterialDismissDto implements Serializable {

    private int id;
    private String code;
    private int categoryId;
    private Date dismissDate;
    private int supplierId;
    private int status;
    private List<MaterialDismissDetailDto> lstDetails;
    private Integer dismissFromId;
    private Integer warehouseId;
    private Integer dismissFrom;
    private String reson;
    private Long total;

    @Override
    public MaterialDismissDto clone(){
        return new MaterialDismissDto(id, code, categoryId, dismissDate, supplierId, status, lstDetails,
                dismissFromId, warehouseId, dismissFrom, reson, total);
    }

    public MaterialDismissDto(int id, String code, int categoryId, Date dismissDate,
                              int supplierId, int status,
                              List<MaterialDismissDetailDto> lstDetails, Integer dismissFromId,
                              Integer warehouseId, Integer dismissFrom, String reson, Long total) {
        this.id = id;
        this.code = code;
        this.categoryId = categoryId;
        this.dismissDate = dismissDate;
        this.supplierId = supplierId;
        this.status = status;
        this.lstDetails = lstDetails;
        this.dismissFromId = dismissFromId;
        this.warehouseId = warehouseId;
        this.dismissFrom = dismissFrom;
        this.reson = reson;
        this.total = total;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public MaterialDismissDto() {

    }

    public Integer getDismissFrom() {
        return dismissFrom;
    }

    public void setDismissFrom(Integer dismissFrom) {
        this.dismissFrom = dismissFrom;
    }

    public Integer getDismissFromId() {
        return dismissFromId;
    }

    public void setDismissFromId(Integer dismissFromId) {
        this.dismissFromId = dismissFromId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<MaterialDismissDetailDto> getLstDetails() {
        return lstDetails;
    }

    public void setLstDetails(List<MaterialDismissDetailDto> lstDetails) {
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

    public Date getDismissDate() {
        return dismissDate;
    }

    public void setDismissDate(Date dismissDate) {
        this.dismissDate = dismissDate;
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

    public MaterialDismissDto(List<MaterialDismissDetailDto> lstDetails) {
        this.lstDetails = lstDetails;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
