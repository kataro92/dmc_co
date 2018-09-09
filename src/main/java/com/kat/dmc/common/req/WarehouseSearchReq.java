package com.kat.dmc.common.req;

import java.io.Serializable;
import java.util.Date;

public class WarehouseSearchReq implements Serializable {
    private Integer warehouseId;
    private Integer type;//Kì
    private Integer searchType;//Kiểu tìm kiếm
    private Date day;//Tìm theo ngày
    private Date fromDay;//Từ ngày
    private Date toDay;//Đến ngày
    private Integer month;
    private Integer fromMonth;
    private Integer toMonth;
    private Integer quater;
    private Integer fromQuater;
    private Integer toQuater;
    private Integer year;
    private Integer fromYear;
    private Integer toYear;

    public WarehouseSearchReq() {
        warehouseId = 0;
        type = 0;
        searchType = 0;
        month = 1;
        fromMonth = 1;
        toMonth = 12;
        quater = 1;
        fromQuater = 1;
        toQuater = 4;
        year = 2018;
        fromYear = 2018;
        toYear = 2018;
    }

    public WarehouseSearchReq(Integer type, Integer searchType, Date day, Date fromDay,
                              Date toDay, Integer month, Integer fromMonth, Integer toMonth,
                              Integer quater, Integer fromQuater, Integer toQuater, Integer year,
                              Integer fromYear, Integer toYear, Integer warehouseId) {
        this.type = type;
        this.searchType = searchType;
        this.day = day;
        this.fromDay = fromDay;
        this.toDay = toDay;
        this.month = month;
        this.fromMonth = fromMonth;
        this.toMonth = toMonth;
        this.quater = quater;
        this.fromQuater = fromQuater;
        this.toQuater = toQuater;
        this.year = year;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.warehouseId = warehouseId;
    }

    @Override
    public WarehouseSearchReq clone(){
        return new WarehouseSearchReq(type, searchType, day, fromDay, toDay, month, fromMonth,
                toMonth, quater, fromQuater, toQuater, year, fromYear, toYear, warehouseId);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getFromDay() {
        return fromDay;
    }

    public void setFromDay(Date fromDay) {
        this.fromDay = fromDay;
    }

    public Date getToDay() {
        return toDay;
    }

    public void setToDay(Date toDay) {
        this.toDay = toDay;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(Integer fromMonth) {
        this.fromMonth = fromMonth;
    }

    public Integer getToMonth() {
        return toMonth;
    }

    public void setToMonth(Integer toMonth) {
        this.toMonth = toMonth;
    }

    public Integer getQuater() {
        return quater;
    }

    public void setQuater(Integer quater) {
        this.quater = quater;
    }

    public Integer getFromQuater() {
        return fromQuater;
    }

    public void setFromQuater(Integer fromQuater) {
        this.fromQuater = fromQuater;
    }

    public Integer getToQuater() {
        return toQuater;
    }

    public void setToQuater(Integer toQuater) {
        this.toQuater = toQuater;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(Integer fromYear) {
        this.fromYear = fromYear;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(Integer toYear) {
        this.toYear = toYear;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
}
