package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcProductEntity;

import java.util.List;

public interface ProductRepo {
    List<DmcProductEntity> findAll();
    List<DmcProductEntity> findAllActive();
    void save(DmcProductEntity userEntity);
    void delete(DmcProductEntity userEntity);
    DmcProductEntity findById(Integer id);
    List<DmcProductEntity> findBySubgroupId(int id);
}
