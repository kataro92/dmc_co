package com.kat.dmc.common.model;

import javax.persistence.*;

@Entity
@Table(name = "dmc_product", schema = "public")
public class DmcProductEntity {
    private String code;
    private String defCode;
    private String name;
    private String producer;
    private String productGroupCode;
    private String productSubgroupCode;
    private String shortDescription;
    private String sortName;
    private Integer status;
    private int id;
    private String unit;
    private String fullCode;
    private Boolean isCodeFixed;

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "def_code")
    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
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
    @Column(name = "producer")
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Basic
    @Column(name = "product_group_code")
    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
    }

    @Basic
    @Column(name = "product_subgroup_code")
    public String getProductSubgroupCode() {
        return productSubgroupCode;
    }

    public void setProductSubgroupCode(String productSubgroupCode) {
        this.productSubgroupCode = productSubgroupCode;
    }

    @Basic
    @Column(name = "short_description")
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Basic
    @Column(name = "sort_name")
    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
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

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "full_code")
    public String getFullCode() {
        return fullCode;
    }

    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    @Basic
    @Column(name = "is_code_fixed")
    public Boolean getCodeFixed() {
        return isCodeFixed;
    }

    public void setCodeFixed(Boolean codeFixed) {
        isCodeFixed = codeFixed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcProductEntity that = (DmcProductEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (defCode != null ? !defCode.equals(that.defCode) : that.defCode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (producer != null ? !producer.equals(that.producer) : that.producer != null) return false;
        if (productGroupCode != null ? !productGroupCode.equals(that.productGroupCode) : that.productGroupCode != null)
            return false;
        if (productSubgroupCode != null ? !productSubgroupCode.equals(that.productSubgroupCode) : that.productSubgroupCode != null)
            return false;
        if (shortDescription != null ? !shortDescription.equals(that.shortDescription) : that.shortDescription != null)
            return false;
        if (sortName != null ? !sortName.equals(that.sortName) : that.sortName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (fullCode != null ? !fullCode.equals(that.fullCode) : that.fullCode != null) return false;
        if (isCodeFixed != null ? !isCodeFixed.equals(that.isCodeFixed) : that.isCodeFixed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (defCode != null ? defCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (productGroupCode != null ? productGroupCode.hashCode() : 0);
        result = 31 * result + (productSubgroupCode != null ? productSubgroupCode.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (sortName != null ? sortName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (fullCode != null ? fullCode.hashCode() : 0);
        result = 31 * result + (isCodeFixed != null ? isCodeFixed.hashCode() : 0);
        return result;
    }
}
