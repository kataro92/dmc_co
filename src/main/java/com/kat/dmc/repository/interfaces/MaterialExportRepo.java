package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialExportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialExportEntity;

import java.util.List;

public interface MaterialExportRepo {
    List<DmcMaterialExportEntity> findAll();
    List<DmcMaterialExportEntity> findAllActive();
    void save(DmcMaterialExportEntity userEntity);
    void delete(DmcMaterialExportEntity userEntity);
    DmcMaterialExportEntity findById(Integer id);
    List<DmcMaterialExportDetailEntity> findAllActiveByMaterialImpId(int materialExport);
    List<DmcMaterialExportEntity> findAllActiveByWarehouseId(Integer id);
}
