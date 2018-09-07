package com.kat.dmc.common.dto;

import java.io.Serializable;

public class JobPositionDto implements Serializable {

    private String code;
    private String jobDuties;
    private String jobFunctions;
    private String jobTitleCode;
    private String name;
    private Integer status;
    private int id;
    private Integer displayWithDeptName;

    public JobPositionDto() {
    }

    public JobPositionDto(String code, String jobDuties, String jobFunctions, String jobTitleCode, String name, Integer status, int id, Integer displayWithDeptName) {
        this.code = code;
        this.jobDuties = jobDuties;
        this.jobFunctions = jobFunctions;
        this.jobTitleCode = jobTitleCode;
        this.name = name;
        this.status = status;
        this.id = id;
        this.displayWithDeptName = displayWithDeptName;
    }

    @Override
    public JobPositionDto clone(){
        return new JobPositionDto(code, jobDuties, jobFunctions, jobTitleCode, name, status, id, displayWithDeptName);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getDisplayWithDeptName() {
        return displayWithDeptName;
    }

    public void setDisplayWithDeptName(Integer displayWithDeptName) {
        this.displayWithDeptName = displayWithDeptName;
    }
}
