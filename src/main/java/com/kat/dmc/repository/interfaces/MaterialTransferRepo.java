package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialTransferDetailEntity;
import com.kat.dmc.common.model.DmcMaterialTransferEntity;

import java.util.List;

public interface MaterialTransferRepo {
    List<DmcMaterialTransferEntity> findAll();
    List<DmcMaterialTransferEntity> findAllActive();
    void save(DmcMaterialTransferEntity userEntity);
    void delete(DmcMaterialTransferEntity userEntity);
    DmcMaterialTransferEntity findById(Integer id);
    List<DmcMaterialTransferDetailEntity> findAllActiveByMaterialImpId(int materialTransfer);
    List<DmcMaterialTransferEntity> findAllActiveByWarehouseId(Integer id);
}
