package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialGroupEntity;

import java.util.List;

public interface MaterialGroupRepo {
    List<DmcMaterialGroupEntity> findAll();
    List<DmcMaterialGroupEntity> findAllActive();
    void save(DmcMaterialGroupEntity userEntity);
    void delete(DmcMaterialGroupEntity userEntity);
    DmcMaterialGroupEntity findById(Integer id);
}
