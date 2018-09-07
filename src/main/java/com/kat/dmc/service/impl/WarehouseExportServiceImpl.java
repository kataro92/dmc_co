package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.MaterialExportDetailDto;
import com.kat.dmc.common.dto.MaterialExportDto;
import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.model.*;
import com.kat.dmc.repository.interfaces.MaterialExportDetailRepo;
import com.kat.dmc.repository.interfaces.MaterialExportRepo;
import com.kat.dmc.service.interfaces.WarehouseExportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseExportServiceImpl implements WarehouseExportService {

    @Autowired
    MaterialExportRepo materialExportRepo;

    @Autowired
    MaterialExportDetailRepo materialExportDetailRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MaterialExportDto> findAll() {
        return materialExportRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialExportDto> findAllActive() {
        return materialExportRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id) {
        return materialExportRepo.findAllActiveByWarehouseId(id).stream().map(this::entity2IETDDto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialExportDetailDto> findAllActiveDtlByMaterialImpId(int materialExport) {
        return materialExportRepo.findAllActiveByMaterialImpId(materialExport).stream().map(this::entity2DetailDto).collect(Collectors.toList());
    }

    @Override
    public void save(MaterialExportDto userDto) {
        DmcMaterialExportEntity savingObj = modelMapper.map(userDto, DmcMaterialExportEntity.class);
        materialExportRepo.save(savingObj);
        if(userDto.getLstDetails() != null && !userDto.getLstDetails().isEmpty()){
           for(MaterialExportDetailDto exportDetailDto : userDto.getLstDetails()){
               DmcMaterialExportDetailEntity savingDtlObj = modelMapper.map(exportDetailDto, DmcMaterialExportDetailEntity.class);
               savingDtlObj.setMaterialExportId(userDto.getId());
               materialExportDetailRepo.save(savingDtlObj);
           }
        }
    }

    @Override
    public void delete(Integer id) {
        materialExportRepo.delete(materialExportRepo.findById(id));
    }

    private MaterialIETDDto entity2IETDDto(DmcMaterialExportEntity entity){
        MaterialIETDDto map = modelMapper.map(entity, MaterialIETDDto.class);
        map.setCreateDate(entity.getExportDate());
//        map.setName(entity.getStatus());
        return map;
    }
    private MaterialExportDto entity2Dto(DmcMaterialExportEntity entity){
        return modelMapper.map(entity, MaterialExportDto.class);
    }

    private MaterialExportDetailDto entity2DetailDto(DmcMaterialExportDetailEntity entity){
        return modelMapper.map(entity, MaterialExportDetailDto.class);
    }
}
