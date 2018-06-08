package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcWarehouseEntity;

import java.util.List;

public interface WarehouseRepo {
    List<DmcWarehouseEntity> findAll();
    List<DmcWarehouseEntity> findAllActive();
    void save(DmcWarehouseEntity userEntity);
    void delete(DmcWarehouseEntity userEntity);
    DmcWarehouseEntity findById(Integer id);
}
