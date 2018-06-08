package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.EmployeeEntity;

import java.util.List;

public interface EmployeeRepo {
    List<EmployeeEntity> findAll();
    List<EmployeeEntity> findByDeptId(int deptId);
    void save(EmployeeEntity userEntity);
    void delete(EmployeeEntity userEntity);
    EmployeeEntity findById(Integer userId);
    void deleteByDeptIdNotIn(List<Integer> lstEmpIds, Integer deptId);
    List<EmployeeDto> findAllActive();
}
