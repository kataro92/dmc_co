package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialExportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;

import java.util.List;

public interface MaterialExportDetailRepo {
    List<DmcMaterialExportDetailEntity> findAll();
    List<DmcMaterialExportDetailEntity> findAllActive();
    void save(DmcMaterialExportDetailEntity userEntity);
    void delete(DmcMaterialExportDetailEntity userEntity);
    DmcMaterialExportDetailEntity findById(Integer id);
    List<DmcMaterialExportDetailEntity> findByIdMaterialId(int id);
}
