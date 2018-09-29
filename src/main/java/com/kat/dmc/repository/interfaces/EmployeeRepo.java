package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.model.DmcEmployeeEntity;

import java.util.List;

public interface EmployeeRepo {
    List<DmcEmployeeEntity> findAll();
    List<DmcEmployeeEntity> findByDeptId(int deptId);
    void save(DmcEmployeeEntity userEntity);
    void delete(DmcEmployeeEntity userEntity);
    DmcEmployeeEntity findById(Integer userId);
    void deleteByDeptIdNotIn(List<Integer> lstEmpIds, Integer deptId);
    List<EmployeeDto> findAllActive();
}
