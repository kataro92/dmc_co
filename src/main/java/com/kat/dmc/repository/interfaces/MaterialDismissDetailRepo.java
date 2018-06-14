package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcMaterialDismissDetailEntity;

import java.util.List;

public interface MaterialDismissDetailRepo {
    List<DmcMaterialDismissDetailEntity> findAll();
    List<DmcMaterialDismissDetailEntity> findAllActive();
    void save(DmcMaterialDismissDetailEntity userEntity);
    void delete(DmcMaterialDismissDetailEntity userEntity);
    DmcMaterialDismissDetailEntity findById(Integer id);
}
