package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.ProductSubgroupDto;

import java.util.List;

public interface ProductSubgroupService {
    List<ProductSubgroupDto> findAll();
    List<ProductSubgroupDto> findAllActive();
    void save(ProductSubgroupDto userDto);
    void delete(Integer id);
    List<ProductSubgroupDto> findAllByGroupId(int id);
}
