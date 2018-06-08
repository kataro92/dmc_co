package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.ProductEntity;

import java.util.List;

public interface ProductRepo {
    List<ProductEntity> findAll();
    List<ProductEntity> findAllActive();
    void save(ProductEntity userEntity);
    void delete(ProductEntity userEntity);
    ProductEntity findById(Integer id);
    List<ProductEntity> findBySubgroupId(int id);
}
