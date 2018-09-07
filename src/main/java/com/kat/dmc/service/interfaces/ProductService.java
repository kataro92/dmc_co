package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    List<ProductDto> findAllActive();
    void save(ProductDto userDto);
    void delete(Integer id);
    List<ProductDto> findBySubgroupId(int id);
}
