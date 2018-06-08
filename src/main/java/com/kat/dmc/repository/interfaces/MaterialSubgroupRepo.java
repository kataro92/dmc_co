package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.MaterialSubgroupEntity;

import java.util.List;

public interface MaterialSubgroupRepo {
    List<MaterialSubgroupEntity> findAll();
    List<MaterialSubgroupEntity> findAllActive();
    void save(MaterialSubgroupEntity userEntity);
    void delete(MaterialSubgroupEntity userEntity);
    MaterialSubgroupEntity findById(Integer id);
    List<MaterialSubgroupEntity> findAllByGroupId(int id);
}
