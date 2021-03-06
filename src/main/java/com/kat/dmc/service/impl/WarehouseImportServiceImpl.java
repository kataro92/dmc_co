package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.dto.MaterialImportDetailDto;
import com.kat.dmc.common.dto.MaterialImportDto;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportEntity;
import com.kat.dmc.repository.interfaces.MaterialImportDetailRepo;
import com.kat.dmc.repository.interfaces.MaterialImportRepo;
import com.kat.dmc.service.interfaces.WarehouseImportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

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
    public List<MaterialImportDto> findAllActiveQuantity() {
        return null;
//        return materialImportRepo.findAllActiveQuantity().stream().map(this::entity2Dto).collect(Collectors.toList());
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
    public List<MaterialImportDto> findMaterialAvaliabeByIdAndQuantity(Integer materialId, Integer quantity) {
        List<MaterialImportDto> lstMaterialImp = new ArrayList<>();
        //Get all material import
        List<MaterialImportDto> allActive = materialImportRepo.findAllActiveByMaterialId(materialId);
        for(MaterialImportDto importDto : allActive){
            if(quantity != 0 && quantity >= importDto.getChildQuantity()){
                lstMaterialImp.add(importDto);
                quantity -= importDto.getChildQuantity();
            }else if(quantity != 0 && quantity < importDto.getChildQuantity()){
                importDto.setChildQuantity(quantity);
                lstMaterialImp.add(importDto);
                return lstMaterialImp;
            }
        }
        return lstMaterialImp;
    }

    @Override
    public void save(MaterialImportDto userDto) {
        DmcMaterialImportEntity savingObj = modelMapper.map(userDto, DmcMaterialImportEntity.class);
        materialImportRepo.save(savingObj);
        if(userDto.getLstDetails() != null && !userDto.getLstDetails().isEmpty()){
           for(MaterialImportDetailDto importDetailDto : userDto.getLstDetails()){
               DmcMaterialImportDetailEntity savingDtlObj = modelMapper.map(importDetailDto, DmcMaterialImportDetailEntity.class);
               savingDtlObj.setMaterialImportId(userDto.getId());
               savingDtlObj.setStatus(savingObj.getStatus());
               materialImportDetailRepo.save(savingDtlObj);
           }
        }
    }

    @Override
    public void delete(Integer id) {
        materialImportRepo.delete(materialImportRepo.findById(id));
    }

    @Override
    public List<Integer> findAllActiveByMaterialId(int id) {
        return materialImportRepo.findAllActiveByMaterialId(id).stream().map(this::getImportId).collect(Collectors.toList());
    }

    private Integer getImportId(MaterialImportDto materialImportDto){
        return materialImportDto.getId();
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
