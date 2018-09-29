package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcProductGroupEntity;

import java.util.List;

public interface ProductGroupRepo {
    List<DmcProductGroupEntity> findAll();
    List<DmcProductGroupEntity> findAllActive();
    void save(DmcProductGroupEntity userEntity);
    void delete(DmcProductGroupEntity userEntity);
    DmcProductGroupEntity findById(Integer id);
}
