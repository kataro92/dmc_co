package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportEntity;
import com.kat.dmc.common.dto.MaterialImportDto;

import java.util.List;

public interface MaterialImportRepo {
    List<DmcMaterialImportEntity> findAll();
    List<DmcMaterialImportEntity> findAllActive();
    List<MaterialImportDto> findAllActiveQuantity();
    void save(DmcMaterialImportEntity userEntity);
    void delete(DmcMaterialImportEntity userEntity);
    DmcMaterialImportEntity findById(Integer id);
    List<DmcMaterialImportDetailEntity> findAllActiveByMaterialImpId(int materialImport);
    List<DmcMaterialImportDetailEntity> findAllActiveByMaterialIdImpId(int materialImport, int materialId);
    List<DmcMaterialImportDetailEntity> findAllActiveByMaterialIdImpIds(List<Integer> materialImport, int materialId);
    List<DmcMaterialImportEntity> findAllActiveByWarehouseId(Integer id);
    List<MaterialImportDto> findAllActiveByMaterialId(Integer id);
    Long countQuantityByWarehouseId(Integer warehouseId);
}
