package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "material", schema = "public")
public class MaterialEntity {
    private String code;
    private Boolean isCodeFixed;
    private String createdBy;
    private Timestamp createdDate;
    private String defCode;
    private String editedBy;
    private Timestamp lastModified;
    private String materialGroupCode;
    private String materialSubgroupCode;
    private String name;
    private String producer;
    private Boolean isPublished;
    private String publishedBy;
    private String shortDescription;
    private String sortName;
    private Integer status;
    private String unit;
    private String valueToSearch;
    private int id;
    private String fullCode;


    @Basic
    @Column(name = "full_code")
    public String getFullCode() {
        return fullCode;
    }

    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "is_code_fixed")
    public Boolean getCodeFixed() {
        return isCodeFixed;
    }

    public void setCodeFixed(Boolean codeFixed) {
        isCodeFixed = codeFixed;
    }

    @Basic
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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
    @Column(name = "edited_by")
    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    @Basic
    @Column(name = "last_modified")
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    @Basic
    @Column(name = "material_group_code")
    public String getMaterialGroupCode() {
        return materialGroupCode;
    }

    public void setMaterialGroupCode(String materialGroupCode) {
        this.materialGroupCode = materialGroupCode;
    }

    @Basic
    @Column(name = "material_subgroup_code")
    public String getMaterialSubgroupCode() {
        return materialSubgroupCode;
    }

    public void setMaterialSubgroupCode(String materialSubgroupCode) {
        this.materialSubgroupCode = materialSubgroupCode;
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
    @Column(name = "is_published")
    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    @Basic
    @Column(name = "published_by")
    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
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

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "value_to_search")
    public String getValueToSearch() {
        return valueToSearch;
    }

    public void setValueToSearch(String valueToSearch) {
        this.valueToSearch = valueToSearch;
    }

    @Id
    @Column(name = "_id")
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

        MaterialEntity that = (MaterialEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (isCodeFixed != null ? !isCodeFixed.equals(that.isCodeFixed) : that.isCodeFixed != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (defCode != null ? !defCode.equals(that.defCode) : that.defCode != null) return false;
        if (editedBy != null ? !editedBy.equals(that.editedBy) : that.editedBy != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (materialGroupCode != null ? !materialGroupCode.equals(that.materialGroupCode) : that.materialGroupCode != null)
            return false;
        if (materialSubgroupCode != null ? !materialSubgroupCode.equals(that.materialSubgroupCode) : that.materialSubgroupCode != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (producer != null ? !producer.equals(that.producer) : that.producer != null) return false;
        if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
        if (publishedBy != null ? !publishedBy.equals(that.publishedBy) : that.publishedBy != null) return false;
        if (shortDescription != null ? !shortDescription.equals(that.shortDescription) : that.shortDescription != null)
            return false;
        if (sortName != null ? !sortName.equals(that.sortName) : that.sortName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (fullCode != null ? !fullCode.equals(that.fullCode) : that.fullCode != null) return false;
        if (valueToSearch != null ? !valueToSearch.equals(that.valueToSearch) : that.valueToSearch != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (isCodeFixed != null ? isCodeFixed.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (defCode != null ? defCode.hashCode() : 0);
        result = 31 * result + (editedBy != null ? editedBy.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (materialGroupCode != null ? materialGroupCode.hashCode() : 0);
        result = 31 * result + (materialSubgroupCode != null ? materialSubgroupCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (publishedBy != null ? publishedBy.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (sortName != null ? sortName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (valueToSearch != null ? valueToSearch.hashCode() : 0);
        result = 31 * result + (fullCode != null ? fullCode.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
