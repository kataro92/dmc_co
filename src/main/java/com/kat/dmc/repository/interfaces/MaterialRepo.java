package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.MaterialDto;
import com.kat.dmc.common.model.MaterialEntity;

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
}
