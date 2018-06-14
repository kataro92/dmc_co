package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialTransferDetailEntity;

import java.util.List;

public interface MaterialTransferDetailRepo {
    List<DmcMaterialTransferDetailEntity> findAll();
    List<DmcMaterialTransferDetailEntity> findAllActive();
    void save(DmcMaterialTransferDetailEntity userEntity);
    void delete(DmcMaterialTransferDetailEntity userEntity);
    DmcMaterialTransferDetailEntity findById(Integer id);
}
