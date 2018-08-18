package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;

import java.util.List;

public interface MaterialImportDetailRepo {
    List<DmcMaterialImportDetailEntity> findAll();
    List<DmcMaterialImportDetailEntity> findAllActive();
    void save(DmcMaterialImportDetailEntity userEntity);
    void delete(DmcMaterialImportDetailEntity userEntity);
    DmcMaterialImportDetailEntity findById(Integer id);
    List<DmcMaterialImportDetailEntity> findByIdMaterialId(int id);
    Long countQuantityByWarehouseId(Integer warehouseId);
}
