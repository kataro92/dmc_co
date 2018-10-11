package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.PropertyDto;
import com.kat.dmc.common.model.DmcPropertyEntity;
import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.common.util.PasswordUtil;
import com.kat.dmc.repository.interfaces.PropertyRepo;
import com.kat.dmc.service.interfaces.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PropertyDto> findAll() {
        return propertyRepo.findAll().stream().map(this::TsProperty2PropertyDto).collect(Collectors.toList());
    }

    @Override
    public void save(PropertyDto propertyDto) {
        DmcPropertyEntity savingObj = propertyRepo.findById(propertyDto.getId());
        if(savingObj != null){
            modelMapper.map(propertyDto, savingObj);
        }else{
            savingObj = modelMapper.map(propertyDto, DmcPropertyEntity.class);
        }
        propertyRepo.save(savingObj);
    }

    @Override
    public void delete(Integer propertyId) {
        DmcPropertyEntity deletingObj = propertyRepo.findById(propertyId);
        propertyRepo.delete(deletingObj);
    }

    @Override
    public List<PropertyDto> findAllByReq(PropertyDto searchProperty) {
        return propertyRepo.findAllByReq(searchProperty).stream().map(this::TsProperty2PropertyDto).collect(Collectors.toList());
    }

    private PropertyDto TsProperty2PropertyDto(DmcPropertyEntity input){
        return modelMapper.map(input, PropertyDto.class);
    }
}
