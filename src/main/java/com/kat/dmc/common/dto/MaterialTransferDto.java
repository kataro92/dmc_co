package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MaterialTransferDto implements Serializable {

    private int id;
    private String code;
    private int categoryId;
    private Date transferDate;
    private int supplierId;
    private int status;
    private List<MaterialTransferDetailDto> lstDetails;
    private Integer transferFromId;
    private Integer warehouseId;
    private Integer transferFrom;
    private String reson;
    private Long total;

    @Override
    public MaterialTransferDto clone(){
        return new MaterialTransferDto(id, code, categoryId, transferDate, supplierId, status, lstDetails,
                transferFromId, warehouseId, transferFrom, reson, total);
    }

    public MaterialTransferDto(int id, String code, int categoryId, Date transferDate,
                               int supplierId, int status,
                               List<MaterialTransferDetailDto> lstDetails, Integer transferFromId,
                               Integer warehouseId, Integer transferFrom, String reson, Long total) {
        this.id = id;
        this.code = code;
        this.categoryId = categoryId;
        this.transferDate = transferDate;
        this.supplierId = supplierId;
        this.status = status;
        this.lstDetails = lstDetails;
        this.transferFromId = transferFromId;
        this.warehouseId = warehouseId;
        this.transferFrom = transferFrom;
        this.reson = reson;
        this.total = total;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public MaterialTransferDto() {

    }

    public Integer getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(Integer transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Integer getTransferFromId() {
        return transferFromId;
    }

    public void setTransferFromId(Integer transferFromId) {
        this.transferFromId = transferFromId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<MaterialTransferDetailDto> getLstDetails() {
        return lstDetails;
    }

    public void setLstDetails(List<MaterialTransferDetailDto> lstDetails) {
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

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
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

    public MaterialTransferDto(List<MaterialTransferDetailDto> lstDetails) {
        this.lstDetails = lstDetails;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
