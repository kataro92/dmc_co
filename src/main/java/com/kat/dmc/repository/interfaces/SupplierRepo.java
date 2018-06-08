package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.SupplierEntity;

import java.util.List;

public interface SupplierRepo {
    List<SupplierEntity> findAll();
    List<SupplierEntity> findAllActive();
    void save(SupplierEntity clientEntity);
    void delete(SupplierEntity clientEntity);
    SupplierEntity findById(Integer clientId);
}
