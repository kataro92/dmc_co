package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialSubgroupEntity;

import java.util.List;

public interface MaterialSubgroupRepo {
    List<DmcMaterialSubgroupEntity> findAll();
    List<DmcMaterialSubgroupEntity> findAllActive();
    void save(DmcMaterialSubgroupEntity userEntity);
    void delete(DmcMaterialSubgroupEntity userEntity);
    DmcMaterialSubgroupEntity findById(Integer id);
    List<DmcMaterialSubgroupEntity> findAllByGroupId(int id);
}
