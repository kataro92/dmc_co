package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.ProductDto;
import com.kat.dmc.common.model.ProductEntity;
import com.kat.dmc.repository.interfaces.ProductRepo;
import com.kat.dmc.service.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductDto> findAll() {
        return productRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllActive() {
        return productRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(ProductDto userDto) {
        ProductEntity savingObj = modelMapper.map(userDto, ProductEntity.class);
        productRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        productRepo.delete(productRepo.findById(id));
    }

    @Override
    public List<ProductDto> findBySubgroupId(int id) {
        return productRepo.findBySubgroupId(id).stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    private ProductDto entity2Dto(ProductEntity entity){
        return modelMapper.map(entity, ProductDto.class);
    }
}
