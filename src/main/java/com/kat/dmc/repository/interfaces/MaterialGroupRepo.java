package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.MaterialGroupEntity;

import java.util.List;

public interface MaterialGroupRepo {
    List<MaterialGroupEntity> findAll();
    List<MaterialGroupEntity> findAllActive();
    void save(MaterialGroupEntity userEntity);
    void delete(MaterialGroupEntity userEntity);
    MaterialGroupEntity findById(Integer id);
}
