package com.kat.dmc.common.dto;

import java.io.Serializable;
import java.util.Date;

public class PropertyDto implements Serializable {

    private Integer id;
    private String code;
    private String name;
    private Integer status;
    private Double buyPrice;
    private Double liquiDatedPrice;
    private Date receiveDate;
    private Date startOfWarrantyDate;
    private Date endOfWarrantyDate;
    private Date nextMaintainceDate;
    private Date lastMaintainceDate;

    public PropertyDto() {
    }

    public PropertyDto(Integer id, String code, String name, Integer status, Double buyPrice, Double liquiDatedPrice, Date receiveDate, Date startOfWarrantyDate, Date endOfWarrantyDate, Date nextMaintainceDate, Date lastMaintainceDate) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.buyPrice = buyPrice;
        this.liquiDatedPrice = liquiDatedPrice;
        this.receiveDate = receiveDate;
        this.startOfWarrantyDate = startOfWarrantyDate;
        this.endOfWarrantyDate = endOfWarrantyDate;
        this.nextMaintainceDate = nextMaintainceDate;
        this.lastMaintainceDate = lastMaintainceDate;
        this.setId(id);
    }

    @Override
    public PropertyDto clone(){
        return new PropertyDto(getId(), code, name, status, buyPrice, liquiDatedPrice, receiveDate, startOfWarrantyDate, endOfWarrantyDate, nextMaintainceDate, lastMaintainceDate);
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getLiquiDatedPrice() {
        return liquiDatedPrice;
    }

    public void setLiquiDatedPrice(Double liquiDatedPrice) {
        this.liquiDatedPrice = liquiDatedPrice;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getStartOfWarrantyDate() {
        return startOfWarrantyDate;
    }

    public void setStartOfWarrantyDate(Date startOfWarrantyDate) {
        this.startOfWarrantyDate = startOfWarrantyDate;
    }

    public Date getEndOfWarrantyDate() {
        return endOfWarrantyDate;
    }

    public void setEndOfWarrantyDate(Date endOfWarrantyDate) {
        this.endOfWarrantyDate = endOfWarrantyDate;
    }

    public Date getNextMaintainceDate() {
        return nextMaintainceDate;
    }

    public void setNextMaintainceDate(Date nextMaintainceDate) {
        this.nextMaintainceDate = nextMaintainceDate;
    }

    public Date getLastMaintainceDate() {
        return lastMaintainceDate;
    }

    public void setLastMaintainceDate(Date lastMaintainceDate) {
        this.lastMaintainceDate = lastMaintainceDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}