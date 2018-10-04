package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.MaterialDismissDetailDto;
import com.kat.dmc.common.dto.MaterialDismissDto;
import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.model.DmcMaterialDismissDetailEntity;
import com.kat.dmc.common.model.DmcMaterialDismissEntity;
import com.kat.dmc.repository.interfaces.MaterialDismissDetailRepo;
import com.kat.dmc.repository.interfaces.MaterialDismissRepo;
import com.kat.dmc.service.interfaces.WarehouseDismissService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

@Service
@Transactional
public class WarehouseDismissServiceImpl implements WarehouseDismissService {

    @Autowired
    MaterialDismissRepo materialDismissRepo;

    @Autowired
    MaterialDismissDetailRepo materialDismissDetailRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MaterialDismissDto> findAll() {
        return materialDismissRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialDismissDto> findAllActive() {
        return materialDismissRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id) {
        return materialDismissRepo.findAllActiveByWarehouseId(id).stream().map(this::entity2IETDDto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialDismissDetailDto> findAllActiveDtlByMaterialImpId(int materialDismiss) {
        return materialDismissRepo.findAllActiveByMaterialImpId(materialDismiss).stream().map(this::entity2DetailDto).collect(Collectors.toList());
    }

    @Override
    public void save(MaterialDismissDto userDto) {
        DmcMaterialDismissEntity savingObj = modelMapper.map(userDto, DmcMaterialDismissEntity.class);
        materialDismissRepo.save(savingObj);
        if(userDto.getLstDetails() != null && !userDto.getLstDetails().isEmpty()){
           for(MaterialDismissDetailDto dismissDetailDto : userDto.getLstDetails()){
               DmcMaterialDismissDetailEntity savingDtlObj = modelMapper.map(dismissDetailDto, DmcMaterialDismissDetailEntity.class);
               savingDtlObj.setMaterialDismissId(userDto.getId());
               savingDtlObj.setStatus(savingObj.getStatus());
               materialDismissDetailRepo.save(savingDtlObj);
           }
        }
    }

    @Override
    public void delete(Integer id) {
        materialDismissRepo.delete(materialDismissRepo.findById(id));
    }

    private MaterialIETDDto entity2IETDDto(DmcMaterialDismissEntity entity){
        MaterialIETDDto map = modelMapper.map(entity, MaterialIETDDto.class);
        map.setCreateDate(entity.getDismissDate());
//        map.setName(entity.getStatus());
        return map;
    }
    private MaterialDismissDto entity2Dto(DmcMaterialDismissEntity entity){
        return modelMapper.map(entity, MaterialDismissDto.class);
    }

    private MaterialDismissDetailDto entity2DetailDto(DmcMaterialDismissDetailEntity entity){
        return modelMapper.map(entity, MaterialDismissDetailDto.class);
    }
}
