package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcSupplierEntity;

import java.util.List;

public interface SupplierRepo {
    List<DmcSupplierEntity> findAll();
    List<DmcSupplierEntity> findAllActive();
    void save(DmcSupplierEntity clientEntity);
    void delete(DmcSupplierEntity clientEntity);
    DmcSupplierEntity findById(Integer clientId);
}
