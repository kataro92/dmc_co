package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcDepartmentEntity;

import java.util.List;

public interface DeptRepo {
    List<DmcDepartmentEntity> findAll();
    void save(DmcDepartmentEntity userEntity);
    void delete(DmcDepartmentEntity userEntity);
    DmcDepartmentEntity findById(Integer userId);
}
