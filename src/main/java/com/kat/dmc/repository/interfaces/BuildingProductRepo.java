package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcBuildingProductEntity;

import java.util.List;

public interface BuildingProductRepo {
    List<DmcBuildingProductEntity> findAll();
    DmcBuildingProductEntity save(DmcBuildingProductEntity userEntity);
    void delete(DmcBuildingProductEntity userEntity);
    DmcBuildingProductEntity findById(Integer userId);
}
