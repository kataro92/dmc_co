package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.List;

public class WarehouseDto implements Serializable {
    private int id;
    private String name;
    private boolean isCanImport;
    private boolean isCanExport;
    private Boolean isCanTransfer;
    private Boolean isCanDismiss;
    private String address;
    private int status;
    private String code;
    private int categoryId;
    private List<MaterialIETDDto> lstImport;
    private List<MaterialIETDDto> lstExport;
    private List<MaterialIETDDto> lstTransfer;
    private List<MaterialIETDDto> lstDismiss;

    private Long totalImportedMaterial;
    private Long totalExportedMaterial;
    private Long totalImportedPaper;
    private Long totalExportedPaper;
    private Long currentMaterial;

    public WarehouseDto() {
    }

    public WarehouseDto(int id, String name, boolean isCanImport,
                        boolean isCanExport, Boolean isCanTransfer, Boolean isCanDismiss, String address,
                        int status, String code, int categoryId,
                        List<MaterialIETDDto> lstImport,
                        List<MaterialIETDDto> lstExport,
                        List<MaterialIETDDto> lstTransfer,
                        List<MaterialIETDDto> lstDismiss,
                        Long totalImportedMaterial,
                        Long totalExportedMaterial,
                        Long totalImportedPaper,
                        Long totalExportedPaper,
                        Long currentMaterial) {
        this.id = id;
        this.name = name;
        this.isCanImport = isCanImport;
        this.isCanExport = isCanExport;
        this.isCanTransfer = isCanTransfer;
        this.isCanDismiss = isCanDismiss;
        this.address = address;
        this.status = status;
        this.code = code;
        this.categoryId = categoryId;
        this.lstImport = lstImport;
        this.lstExport = lstExport;
        this.lstTransfer = lstTransfer;
        this.lstDismiss = lstDismiss;
        this.totalImportedMaterial = totalImportedMaterial;
        this.totalExportedMaterial = totalExportedMaterial;
        this.totalImportedPaper = totalImportedPaper;
        this.totalExportedPaper = totalExportedPaper;
        this.currentMaterial = currentMaterial;
    }

    @Override
    public WarehouseDto clone(){
        return new WarehouseDto(id, name, isCanImport, isCanExport, isCanTransfer, isCanDismiss,
                address, status, code, categoryId, lstImport, lstExport, lstTransfer, lstDismiss,
                totalImportedMaterial,totalExportedMaterial,totalImportedPaper,totalExportedPaper,currentMaterial);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanImport() {
        return isCanImport;
    }

    public void setCanImport(boolean canImport) {
        isCanImport = canImport;
    }

    public boolean isCanExport() {
        return isCanExport;
    }

    public void setCanExport(boolean canExport) {
        isCanExport = canExport;
    }

    public Boolean getCanTransfer() {
        return isCanTransfer;
    }

    public void setCanTransfer(Boolean canTransfer) {
        isCanTransfer = canTransfer;
    }

    public Boolean getCanDismiss() {
        return isCanDismiss;
    }

    public void setCanDismiss(Boolean canDismiss) {
        isCanDismiss = canDismiss;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MaterialIETDDto> getLstImport() {
        return lstImport;
    }

    public void setLstImport(List<MaterialIETDDto> lstImport) {
        this.lstImport = lstImport;
    }

    public List<MaterialIETDDto> getLstExport() {
        return lstExport;
    }

    public void setLstExport(List<MaterialIETDDto> lstExport) {
        this.lstExport = lstExport;
    }

    public List<MaterialIETDDto> getLstTransfer() {
        return lstTransfer;
    }

    public void setLstTransfer(List<MaterialIETDDto> lstTransfer) {
        this.lstTransfer = lstTransfer;
    }

    public List<MaterialIETDDto> getLstDismiss() {
        return lstDismiss;
    }

    public void setLstDismiss(List<MaterialIETDDto> lstDismiss) {
        this.lstDismiss = lstDismiss;
    }

    public Long getTotalImportedMaterial() {
        return totalImportedMaterial;
    }

    public void setTotalImportedMaterial(Long totalImportedMaterial) {
        this.totalImportedMaterial = totalImportedMaterial;
    }

    public Long getTotalExportedMaterial() {
        return totalExportedMaterial;
    }

    public void setTotalExportedMaterial(Long totalExportedMaterial) {
        this.totalExportedMaterial = totalExportedMaterial;
    }

    public Long getTotalImportedPaper() {
        return totalImportedPaper;
    }

    public void setTotalImportedPaper(Long totalImportedPaper) {
        this.totalImportedPaper = totalImportedPaper;
    }

    public Long getTotalExportedPaper() {
        return totalExportedPaper;
    }

    public void setTotalExportedPaper(Long totalExportedPaper) {
        this.totalExportedPaper = totalExportedPaper;
    }

    public Long getCurrentMaterial() {
        return currentMaterial;
    }

    public void setCurrentMaterial(Long currentMaterial) {
        this.currentMaterial = currentMaterial;
    }
}
