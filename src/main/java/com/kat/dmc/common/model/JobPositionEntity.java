package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "job_position", schema = "public")
public class JobPositionEntity {
    private String code;
    private String createdBy;
    private Timestamp createdDate;
    private String editedBy;
    private String jobDuties;
    private String jobFunctions;
    private String jobTitleCode;
    private Timestamp lastModified;
    private String name;
    private Boolean isPublished;
    private Integer status;
    private String valueToSearch;
    private int id;
    private Integer displayWithDeptName;

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "edited_by")
    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    @Basic
    @Column(name = "job_duties")
    public String getJobDuties() {
        return jobDuties;
    }

    public void setJobDuties(String jobDuties) {
        this.jobDuties = jobDuties;
    }

    @Basic
    @Column(name = "job_functions")
    public String getJobFunctions() {
        return jobFunctions;
    }

    public void setJobFunctions(String jobFunctions) {
        this.jobFunctions = jobFunctions;
    }

    @Basic
    @Column(name = "job_title_code")
    public String getJobTitleCode() {
        return jobTitleCode;
    }

    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Basic
    @Column(name = "display_with_dept_name")
    public Integer getDisplayWithDeptName() {
        return displayWithDeptName;
    }

    public void setDisplayWithDeptName(Integer displayWithDeptName) {
        this.displayWithDeptName = displayWithDeptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobPositionEntity that = (JobPositionEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (editedBy != null ? !editedBy.equals(that.editedBy) : that.editedBy != null) return false;
        if (jobDuties != null ? !jobDuties.equals(that.jobDuties) : that.jobDuties != null) return false;
        if (jobFunctions != null ? !jobFunctions.equals(that.jobFunctions) : that.jobFunctions != null) return false;
        if (jobTitleCode != null ? !jobTitleCode.equals(that.jobTitleCode) : that.jobTitleCode != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (valueToSearch != null ? !valueToSearch.equals(that.valueToSearch) : that.valueToSearch != null)
            return false;
        if (displayWithDeptName != null ? !displayWithDeptName.equals(that.displayWithDeptName) : that.displayWithDeptName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (editedBy != null ? editedBy.hashCode() : 0);
        result = 31 * result + (jobDuties != null ? jobDuties.hashCode() : 0);
        result = 31 * result + (jobFunctions != null ? jobFunctions.hashCode() : 0);
        result = 31 * result + (jobTitleCode != null ? jobTitleCode.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (valueToSearch != null ? valueToSearch.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (displayWithDeptName != null ? displayWithDeptName.hashCode() : 0);
        return result;
    }
}
