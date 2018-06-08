package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.ProductSubgroupDto;
import com.kat.dmc.common.model.ProductSubgroupEntity;
import com.kat.dmc.repository.interfaces.ProductSubgroupRepo;
import com.kat.dmc.service.interfaces.ProductSubgroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductSubgroupServiceImpl implements ProductSubgroupService {

    @Autowired
    ProductSubgroupRepo productSubgroupRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductSubgroupDto> findAll() {
        return productSubgroupRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<ProductSubgroupDto> findAllActive() {
        return productSubgroupRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(ProductSubgroupDto userDto) {
        ProductSubgroupEntity savingObj = modelMapper.map(userDto, ProductSubgroupEntity.class);
        productSubgroupRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        productSubgroupRepo.delete(productSubgroupRepo.findById(id));
    }

    @Override
    public List<ProductSubgroupDto> findAllByGroupId(int id) {
        return productSubgroupRepo.findAllByGroupId(id).stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    private ProductSubgroupDto entity2Dto(ProductSubgroupEntity entity){
        return modelMapper.map(entity, ProductSubgroupDto.class);
    }
}
