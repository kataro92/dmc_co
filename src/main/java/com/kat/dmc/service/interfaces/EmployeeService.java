package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findByDeptId(int id);
    List<EmployeeDto> findAllActive();
}

