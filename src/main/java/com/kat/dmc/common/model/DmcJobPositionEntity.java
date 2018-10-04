package com.kat.dmc.common.model;

import javax.persistence.*;

@Entity
@Table(name = "dmc_job_position", schema = "public")
public class DmcJobPositionEntity {
    private String code;
    private String jobDuties;
    private String jobFunctions;
    private String jobTitleCode;
    private String name;
    private Integer status;
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

        DmcJobPositionEntity that = (DmcJobPositionEntity) o;

        if (id != that.id) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (jobDuties != null ? !jobDuties.equals(that.jobDuties) : that.jobDuties != null) return false;
        if (jobFunctions != null ? !jobFunctions.equals(that.jobFunctions) : that.jobFunctions != null) return false;
        if (jobTitleCode != null ? !jobTitleCode.equals(that.jobTitleCode) : that.jobTitleCode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (displayWithDeptName != null ? !displayWithDeptName.equals(that.displayWithDeptName) : that.displayWithDeptName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (jobDuties != null ? jobDuties.hashCode() : 0);
        result = 31 * result + (jobFunctions != null ? jobFunctions.hashCode() : 0);
        result = 31 * result + (jobTitleCode != null ? jobTitleCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (displayWithDeptName != null ? displayWithDeptName.hashCode() : 0);
        return result;
    }
}
