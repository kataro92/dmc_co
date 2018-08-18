package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcBuildingMaterialEntity;

import java.util.List;

public interface BuildingMaterialRepo {
    List<DmcBuildingMaterialEntity> findAll();
    void save(DmcBuildingMaterialEntity userEntity);
    void delete(DmcBuildingMaterialEntity userEntity);
    DmcBuildingMaterialEntity findById(Integer userId);
}
