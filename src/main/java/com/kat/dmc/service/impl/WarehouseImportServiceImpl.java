package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.*;
import com.kat.dmc.repository.interfaces.MaterialImportDetailRepo;
import com.kat.dmc.repository.interfaces.MaterialImportRepo;
import com.kat.dmc.service.interfaces.WarehouseImportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseImportServiceImpl implements WarehouseImportService {

    @Autowired
    MaterialImportRepo materialImportRepo;

    @Autowired
    MaterialImportDetailRepo materialImportDetailRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MaterialImportDto> findAll() {
        return materialImportRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialImportDto> findAllActive() {
        return materialImportRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id) {
        return materialImportRepo.findAllActiveByWarehouseId(id).stream().map(this::entity2IETDDto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialImportDetailDto> findAllActiveDtlByMaterialImpId(int materialImport) {
        return materialImportRepo.findAllActiveByMaterialImpId(materialImport).stream().map(this::entity2DetailDto).collect(Collectors.toList());
    }

    @Override
    public void save(MaterialImportDto userDto) {
        DmcMaterialImportEntity savingObj = modelMapper.map(userDto, DmcMaterialImportEntity.class);
        materialImportRepo.save(savingObj);
        if(userDto.getLstDetails() != null && !userDto.getLstDetails().isEmpty()){
           for(MaterialImportDetailDto importDetailDto : userDto.getLstDetails()){
               DmcMaterialImportDetailEntity savingDtlObj = modelMapper.map(importDetailDto, DmcMaterialImportDetailEntity.class);
               savingDtlObj.setMaterialImportId(userDto.getId());
               materialImportDetailRepo.save(savingDtlObj);
           }
        }
    }

    @Override
    public void delete(Integer id) {
        materialImportRepo.delete(materialImportRepo.findById(id));
    }

    private MaterialIETDDto entity2IETDDto(DmcMaterialImportEntity entity){
        MaterialIETDDto map = modelMapper.map(entity, MaterialIETDDto.class);
        map.setCreateDate(entity.getImportDate());
//        map.setName(entity.getStatus());
        return map;
    }
    private MaterialImportDto entity2Dto(DmcMaterialImportEntity entity){
        return modelMapper.map(entity, MaterialImportDto.class);
    }

    private MaterialImportDetailDto entity2DetailDto(DmcMaterialImportDetailEntity entity){
        return modelMapper.map(entity, MaterialImportDetailDto.class);
    }
}
