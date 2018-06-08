package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.ProductGroupEntity;

import java.util.List;

public interface ProductGroupRepo {
    List<ProductGroupEntity> findAll();
    List<ProductGroupEntity> findAllActive();
    void save(ProductGroupEntity userEntity);
    void delete(ProductGroupEntity userEntity);
    ProductGroupEntity findById(Integer id);
}
