package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.ProductGroupDto;
import com.kat.dmc.common.model.ProductGroupEntity;
import com.kat.dmc.repository.interfaces.ProductGroupRepo;
import com.kat.dmc.service.interfaces.ProductGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductGroupServiceImpl implements ProductGroupService {

    @Autowired
    ProductGroupRepo productGroupRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProductGroupDto> findAll() {
        return productGroupRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<ProductGroupDto> findAllActive() {
        return productGroupRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(ProductGroupDto userDto) {
        ProductGroupEntity savingObj = modelMapper.map(userDto, ProductGroupEntity.class);
        productGroupRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        productGroupRepo.delete(productGroupRepo.findById(id));
    }

    private ProductGroupDto entity2Dto(ProductGroupEntity entity){
        return modelMapper.map(entity, ProductGroupDto.class);
    }
}
