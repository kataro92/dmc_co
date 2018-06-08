package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.MaterialSubgroupDto;
import com.kat.dmc.common.model.MaterialSubgroupEntity;
import com.kat.dmc.repository.interfaces.MaterialSubgroupRepo;
import com.kat.dmc.service.interfaces.MaterialSubgroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialSubgroupServiceImpl implements MaterialSubgroupService {

    @Autowired
    MaterialSubgroupRepo materialSubgroupRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MaterialSubgroupDto> findAll() {
        return materialSubgroupRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialSubgroupDto> findAllActive() {
        return materialSubgroupRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(MaterialSubgroupDto userDto) {
        MaterialSubgroupEntity savingObj = modelMapper.map(userDto, MaterialSubgroupEntity.class);
        materialSubgroupRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        materialSubgroupRepo.delete(materialSubgroupRepo.findById(id));
    }

    @Override
    public List<MaterialSubgroupDto> findAllByGroupId(int id) {
        return materialSubgroupRepo.findAllByGroupId(id).stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    private MaterialSubgroupDto entity2Dto(MaterialSubgroupEntity entity){
        return modelMapper.map(entity, MaterialSubgroupDto.class);
    }
}
