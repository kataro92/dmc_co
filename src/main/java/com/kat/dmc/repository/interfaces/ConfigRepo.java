package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcConfigEntity;

import java.util.List;

public interface ConfigRepo {
    List<DmcConfigEntity> findAll();
    void save(DmcConfigEntity userEntity);
    void delete(DmcConfigEntity userEntity);
    DmcConfigEntity findById(Integer id);
}
