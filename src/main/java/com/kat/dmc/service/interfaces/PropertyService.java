package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.PropertyDto;

import java.util.List;

public interface PropertyService {
    List<PropertyDto> findAll();
    void save(PropertyDto propertyDto);
    void delete(Integer propertyId);

    List<PropertyDto> findAllByReq(PropertyDto searchProperty);
}
