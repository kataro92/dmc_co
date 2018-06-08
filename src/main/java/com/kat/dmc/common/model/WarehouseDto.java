package com.kat.dmc.common.model;

import java.io.Serializable;

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

    public WarehouseDto() {
    }

    public WarehouseDto(int id, String name, boolean isCanImport, boolean isCanExport, Boolean isCanTransfer, Boolean isCanDismiss, String address, int status, String code) {
        this.id = id;
        this.name = name;
        this.isCanImport = isCanImport;
        this.isCanExport = isCanExport;
        this.isCanTransfer = isCanTransfer;
        this.isCanDismiss = isCanDismiss;
        this.address = address;
        this.status = status;
        this.code = code;
    }

    @Override
    public WarehouseDto clone(){
        return new WarehouseDto(id, name, isCanImport, isCanExport, isCanTransfer, isCanDismiss, address, status, code);
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
}
