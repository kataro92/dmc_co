package com.kat.dmc.common.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class DepartmentDto implements Serializable {
    private String defCode;
    private String description;
    private String name;
    private String parentCode;
    private Integer status;
    private int id;
    private List<EmployeeDto> lstEmployees;
    private List<DepartmentDto> lstChildDept;

    public DepartmentDto() {
    }

    public DepartmentDto(String defCode, String description,
                         String name, String parentCode, Integer status, int id, List<EmployeeDto> lstEmployees,
                         List<DepartmentDto> lstChildDept) {
        this.defCode = defCode;
        this.description = description;
        this.name = name;
        this.parentCode = parentCode;
        this.status = status;
        this.id = id;
        this.lstEmployees = lstEmployees;
        this.lstChildDept = lstChildDept;
    }

    @Override
    public DepartmentDto clone(){
        return new DepartmentDto(defCode, description, name, parentCode, status, id, lstEmployees, lstChildDept);
    }

    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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

    public List<EmployeeDto> getLstEmployees() {
        return lstEmployees;
    }

    public void setLstEmployees(List<EmployeeDto> lstEmployees) {
        this.lstEmployees = lstEmployees;
    }

    public List<DepartmentDto> getLstChildDept() {
        return lstChildDept;
    }

    public void setLstChildDept(List<DepartmentDto> lstChildDept) {
        this.lstChildDept = lstChildDept;
    }
}
