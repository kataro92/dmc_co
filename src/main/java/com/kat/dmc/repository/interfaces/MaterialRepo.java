package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.dto.MaterialDto;
import com.kat.dmc.common.dto.MaterialImportDetailDto;
import com.kat.dmc.common.model.DmcMaterialEntity;

import java.util.List;

public interface MaterialRepo {
    List<DmcMaterialEntity> findAll();
    List<DmcMaterialEntity> findAllActive();
    void save(DmcMaterialEntity userEntity);
    void delete(DmcMaterialEntity userEntity);
    DmcMaterialEntity findById(Integer id);
    List<DmcMaterialEntity> findBySubgroupId(int id);
    DmcMaterialEntity findByCode(String code);
    List<MaterialDto> findAllByImport();
    List<MaterialImportDetailDto> findImpIdsByMaterialId(int id);
}
