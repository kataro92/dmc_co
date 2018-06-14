package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialDismissDetailEntity;
import com.kat.dmc.common.model.DmcMaterialDismissEntity;

import java.util.List;

public interface MaterialDismissRepo {
    List<DmcMaterialDismissEntity> findAll();
    List<DmcMaterialDismissEntity> findAllActive();
    void save(DmcMaterialDismissEntity userEntity);
    void delete(DmcMaterialDismissEntity userEntity);
    DmcMaterialDismissEntity findById(Integer id);
    List<DmcMaterialDismissDetailEntity> findAllActiveByMaterialImpId(int materialDismiss);
    List<DmcMaterialDismissEntity> findAllActiveByWarehouseId(Integer id);
}
