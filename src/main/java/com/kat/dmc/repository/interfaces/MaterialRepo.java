package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.dto.MaterialDto;
import com.kat.dmc.common.model.MaterialEntity;
import com.kat.dmc.common.dto.MaterialImportDetailDto;

import java.util.List;

public interface MaterialRepo {
    List<MaterialEntity> findAll();
    List<MaterialEntity> findAllActive();
    void save(MaterialEntity userEntity);
    void delete(MaterialEntity userEntity);
    MaterialEntity findById(Integer id);
    List<MaterialEntity> findBySubgroupId(int id);
    MaterialEntity findByCode(String code);
    List<MaterialDto> findAllByImport();
    List<MaterialImportDetailDto> findImpIdsByMaterialId(int id);
}
