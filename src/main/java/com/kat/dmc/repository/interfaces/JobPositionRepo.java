package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcJobPositionEntity;

import java.util.List;

public interface JobPositionRepo {
    List<DmcJobPositionEntity> findAll();
    List<DmcJobPositionEntity> findAllActive();
    void save(DmcJobPositionEntity userEntity);
    void delete(DmcJobPositionEntity userEntity);
    DmcJobPositionEntity findById(Integer id);
}
