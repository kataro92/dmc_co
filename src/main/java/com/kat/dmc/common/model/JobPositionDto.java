package com.kat.dmc.common.model;

import java.io.Serializable;

public class JobPositionDto implements Serializable {

    private String code;
    private String defCode;
    private String departmentCode;
    private String jobDuties;
    private String jobFunctions;
    private String jobTitleCode;
    private String name;
    private Integer status;
    private int id;

    public JobPositionDto() {
    }

    public JobPositionDto(String code, String defCode, String departmentCode, String jobDuties, String jobFunctions, String jobTitleCode, String name, Integer status, int id) {
        this.code = code;
        this.defCode = defCode;
        this.departmentCode = departmentCode;
        this.jobDuties = jobDuties;
        this.jobFunctions = jobFunctions;
        this.jobTitleCode = jobTitleCode;
        this.name = name;
        this.status = status;
        this.id = id;
    }

    @Override
    public JobPositionDto clone(){
        return new JobPositionDto(code, defCode, departmentCode, jobDuties, jobFunctions, jobTitleCode, name, status, id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getJobDuties() {
        return jobDuties;
    }

    public void setJobDuties(String jobDuties) {
        this.jobDuties = jobDuties;
    }

    public String getJobFunctions() {
        return jobFunctions;
    }

    public void setJobFunctions(String jobFunctions) {
        this.jobFunctions = jobFunctions;
    }

    public String getJobTitleCode() {
        return jobTitleCode;
    }

    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
