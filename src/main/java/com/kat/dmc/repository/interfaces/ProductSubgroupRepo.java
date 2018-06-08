package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.ProductSubgroupEntity;

import java.util.List;

public interface ProductSubgroupRepo {
    List<ProductSubgroupEntity> findAll();
    List<ProductSubgroupEntity> findAllActive();
    void save(ProductSubgroupEntity userEntity);
    void delete(ProductSubgroupEntity userEntity);
    ProductSubgroupEntity findById(Integer id);
    List<ProductSubgroupEntity> findAllByGroupId(int id);
}
