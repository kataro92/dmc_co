package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MaterialExportDto implements Serializable {

    private int id;
    private String code;
    private int categoryId;
    private Date exportDate;
    private int supplierId;
    private int status;
    private List<MaterialExportDetailDto> lstDetails;
    private Integer exportFromId;
    private Integer warehouseId;
    private Integer exportFrom;
    private String reson;
    private Long total;

    @Override
    public MaterialExportDto clone(){
        return new MaterialExportDto(id, code, categoryId, exportDate, supplierId, status, lstDetails,
                exportFromId, warehouseId, exportFrom, reson, total);
    }

    public MaterialExportDto(int id, String code, int categoryId, Date exportDate,
                             int supplierId, int status,
                             List<MaterialExportDetailDto> lstDetails, Integer exportFromId,
                             Integer warehouseId, Integer exportFrom, String reson, Long total) {
        this.id = id;
        this.code = code;
        this.categoryId = categoryId;
        this.exportDate = exportDate;
        this.supplierId = supplierId;
        this.status = status;
        this.lstDetails = lstDetails;
        this.exportFromId = exportFromId;
        this.warehouseId = warehouseId;
        this.exportFrom = exportFrom;
        this.reson = reson;
        this.total = total;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public MaterialExportDto() {

    }

    public Integer getExportFrom() {
        return exportFrom;
    }

    public void setExportFrom(Integer exportFrom) {
        this.exportFrom = exportFrom;
    }

    public Integer getExportFromId() {
        return exportFromId;
    }

    public void setExportFromId(Integer exportFromId) {
        this.exportFromId = exportFromId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<MaterialExportDetailDto> getLstDetails() {
        return lstDetails;
    }

    public void setLstDetails(List<MaterialExportDetailDto> lstDetails) {
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

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
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

    public MaterialExportDto(List<MaterialExportDetailDto> lstDetails) {
        this.lstDetails = lstDetails;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
