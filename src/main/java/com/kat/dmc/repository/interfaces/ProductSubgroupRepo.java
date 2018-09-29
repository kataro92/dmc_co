package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcProductSubgroupEntity;

import java.util.List;

public interface ProductSubgroupRepo {
    List<DmcProductSubgroupEntity> findAll();
    List<DmcProductSubgroupEntity> findAllActive();
    void save(DmcProductSubgroupEntity userEntity);
    void delete(DmcProductSubgroupEntity userEntity);
    DmcProductSubgroupEntity findById(Integer id);
    List<DmcProductSubgroupEntity> findAllByGroupId(int id);
}
