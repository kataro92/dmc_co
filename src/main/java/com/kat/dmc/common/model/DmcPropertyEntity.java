package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_property", schema = "public")
public class DmcPropertyEntity {
    private String code;
    private String name;
    private Integer status;
    private Double buyPrice;
    private Double liquiDatedPrice;
    private Timestamp receiveDate;
    private Timestamp startOfWarrantyDate;
    private Timestamp endOfWarrantyDate;
    private Timestamp nextMaintainceDate;
    private Timestamp lastMaintainceDate;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Id
    @Column(name = "id")
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

        DmcPropertyEntity that = (DmcPropertyEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (buyPrice != null ? !buyPrice.equals(that.buyPrice) : that.buyPrice != null) return false;
        if (liquiDatedPrice != null ? !liquiDatedPrice.equals(that.liquiDatedPrice) : that.liquiDatedPrice != null) return false;
        if (receiveDate != null ? !receiveDate.equals(that.receiveDate) : that.receiveDate != null) return false;
        if (startOfWarrantyDate != null ? !startOfWarrantyDate.equals(that.startOfWarrantyDate) : that.startOfWarrantyDate != null) return false;
        if (endOfWarrantyDate != null ? !endOfWarrantyDate.equals(that.endOfWarrantyDate) : that.endOfWarrantyDate != null) return false;
        if (nextMaintainceDate != null ? !nextMaintainceDate.equals(that.nextMaintainceDate) : that.nextMaintainceDate != null) return false;
        if (lastMaintainceDate != null ? !lastMaintainceDate.equals(that.lastMaintainceDate) : that.lastMaintainceDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (buyPrice != null ? buyPrice.hashCode() : 0);
        result = 31 * result + (liquiDatedPrice != null ? liquiDatedPrice.hashCode() : 0);
        result = 31 * result + (receiveDate != null ? receiveDate.hashCode() : 0);
        result = 31 * result + (startOfWarrantyDate != null ? startOfWarrantyDate.hashCode() : 0);
        result = 31 * result + (endOfWarrantyDate != null ? endOfWarrantyDate.hashCode() : 0);
        result = 31 * result + (nextMaintainceDate != null ? nextMaintainceDate.hashCode() : 0);
        result = 31 * result + (lastMaintainceDate != null ? lastMaintainceDate.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Basic
    @Column(name = "buy_price")
    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Basic
    @Column(name = "liqui_dated_price")
    public Double getLiquiDatedPrice() {
        return liquiDatedPrice;
    }

    public void setLiquiDatedPrice(Double liquiDatedPrice) {
        this.liquiDatedPrice = liquiDatedPrice;
    }

    @Basic
    @Column(name = "receive_date")
    public Timestamp getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Timestamp receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Basic
    @Column(name = "start_of_warranty_date")
    public Timestamp getStartOfWarrantyDate() {
        return startOfWarrantyDate;
    }

    public void setStartOfWarrantyDate(Timestamp startOfWarrantyDate) {
        this.startOfWarrantyDate = startOfWarrantyDate;
    }

    @Basic
    @Column(name = "end_of_warranty_date")
    public Timestamp getEndOfWarrantyDate() {
        return endOfWarrantyDate;
    }

    public void setEndOfWarrantyDate(Timestamp endOfWarrantyDate) {
        this.endOfWarrantyDate = endOfWarrantyDate;
    }

    @Basic
    @Column(name = "next_maintain_date")
    public Timestamp getNextMaintainceDate() {
        return nextMaintainceDate;
    }

    public void setNextMaintainceDate(Timestamp nextMaintainceDate) {
        this.nextMaintainceDate = nextMaintainceDate;
    }

    @Basic
    @Column(name = "last_maintain_date")
    public Timestamp getLastMaintainceDate() {
        return lastMaintainceDate;
    }

    public void setLastMaintainceDate(Timestamp lastMaintainceDate) {
        this.lastMaintainceDate = lastMaintainceDate;
    }
}
