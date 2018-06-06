package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.DepartmentDto;
import com.kat.dmc.common.model.EmployeeDto;

import java.util.List;

public interface DeptService {
    List<DepartmentDto> findAll();
    void delete(int id);
    void save(DepartmentDto selectedDept);
    List<EmployeeDto> findEmpByDeptId(int deptId);
}
