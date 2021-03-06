package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.MaterialGroupDto;
import com.kat.dmc.common.model.DmcMaterialGroupEntity;
import com.kat.dmc.repository.interfaces.MaterialGroupRepo;
import com.kat.dmc.service.interfaces.MaterialGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialGroupServiceImpl implements MaterialGroupService {

    @Autowired
    MaterialGroupRepo materialGroupRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MaterialGroupDto> findAll() {
        return materialGroupRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialGroupDto> findAllActive() {
        return materialGroupRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(MaterialGroupDto userDto) {
        DmcMaterialGroupEntity savingObj = modelMapper.map(userDto, DmcMaterialGroupEntity.class);
        materialGroupRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        materialGroupRepo.delete(materialGroupRepo.findById(id));
    }

    private MaterialGroupDto entity2Dto(DmcMaterialGroupEntity entity){
        return modelMapper.map(entity, MaterialGroupDto.class);
    }
}
