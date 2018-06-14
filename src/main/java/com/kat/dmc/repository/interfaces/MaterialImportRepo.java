package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportEntity;

import java.util.List;

public interface MaterialImportRepo {
    List<DmcMaterialImportEntity> findAll();
    List<DmcMaterialImportEntity> findAllActive();
    void save(DmcMaterialImportEntity userEntity);
    void delete(DmcMaterialImportEntity userEntity);
    DmcMaterialImportEntity findById(Integer id);
    List<DmcMaterialImportDetailEntity> findAllActiveByMaterialImpId(int materialImport);
    List<DmcMaterialImportEntity> findAllActiveByWarehouseId(Integer id);
}
