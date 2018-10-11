package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.dto.PropertyDto;
import com.kat.dmc.common.model.DmcPropertyEntity;

import java.util.List;

public interface PropertyRepo {
    List<DmcPropertyEntity> findAll();
    void save(DmcPropertyEntity dmcPropertyEntity);
    void delete(DmcPropertyEntity dmcPropertyEntity);
    DmcPropertyEntity findById(Integer propertyId);

    List<DmcPropertyEntity> findAllByReq(PropertyDto searchProperty);
}
