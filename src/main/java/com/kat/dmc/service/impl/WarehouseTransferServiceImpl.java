package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.dto.MaterialTransferDetailDto;
import com.kat.dmc.common.dto.MaterialTransferDto;
import com.kat.dmc.common.model.*;
import com.kat.dmc.repository.interfaces.MaterialTransferDetailRepo;
import com.kat.dmc.repository.interfaces.MaterialTransferRepo;
import com.kat.dmc.service.interfaces.WarehouseTransferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseTransferServiceImpl implements WarehouseTransferService {

    @Autowired
    MaterialTransferRepo materialTransferRepo;

    @Autowired
    MaterialTransferDetailRepo materialTransferDetailRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MaterialTransferDto> findAll() {
        return materialTransferRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialTransferDto> findAllActive() {
        return materialTransferRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id) {
        return materialTransferRepo.findAllActiveByWarehouseId(id).stream().map(this::entity2IETDDto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialTransferDetailDto> findAllActiveDtlByMaterialImpId(int materialTransfer) {
        return materialTransferRepo.findAllActiveByMaterialImpId(materialTransfer).stream().map(this::entity2DetailDto).collect(Collectors.toList());
    }

    @Override
    public void save(MaterialTransferDto userDto) {
        DmcMaterialTransferEntity savingObj = modelMapper.map(userDto, DmcMaterialTransferEntity.class);
        materialTransferRepo.save(savingObj);
        if(userDto.getLstDetails() != null && !userDto.getLstDetails().isEmpty()){
           for(MaterialTransferDetailDto transferDetailDto : userDto.getLstDetails()){
               DmcMaterialTransferDetailEntity savingDtlObj = modelMapper.map(transferDetailDto, DmcMaterialTransferDetailEntity.class);
               savingDtlObj.setMaterialTransferId(userDto.getId());
               materialTransferDetailRepo.save(savingDtlObj);
           }
        }
    }

    @Override
    public void delete(Integer id) {
        materialTransferRepo.delete(materialTransferRepo.findById(id));
    }

    private MaterialIETDDto entity2IETDDto(DmcMaterialTransferEntity entity){
        MaterialIETDDto map = modelMapper.map(entity, MaterialIETDDto.class);
        map.setCreateDate(entity.getTransferDate());
//        map.setName(entity.getStatus());
        return map;
    }
    private MaterialTransferDto entity2Dto(DmcMaterialTransferEntity entity){
        return modelMapper.map(entity, MaterialTransferDto.class);
    }

    private MaterialTransferDetailDto entity2DetailDto(DmcMaterialTransferDetailEntity entity){
        return modelMapper.map(entity, MaterialTransferDetailDto.class);
    }
}
