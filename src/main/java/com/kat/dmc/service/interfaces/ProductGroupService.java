package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.ProductGroupDto;

import java.util.List;

public interface ProductGroupService {
    List<ProductGroupDto> findAll();
    List<ProductGroupDto> findAllActive();
    void save(ProductGroupDto userDto);
    void delete(Integer id);
}
