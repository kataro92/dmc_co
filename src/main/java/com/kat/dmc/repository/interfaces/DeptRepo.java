package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DepartmentEntity;

import java.util.List;

public interface DeptRepo {
    List<DepartmentEntity> findAll();
    void save(DepartmentEntity userEntity);
    void delete(DepartmentEntity userEntity);
    DepartmentEntity findById(Integer userId);
}
