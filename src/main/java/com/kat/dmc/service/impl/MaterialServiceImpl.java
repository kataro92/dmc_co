package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.MaterialDto;
import com.kat.dmc.common.model.MaterialEntity;
import com.kat.dmc.repository.interfaces.MaterialRepo;
import com.kat.dmc.service.interfaces.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepo materialRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<MaterialDto> findAll() {
        return materialRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<MaterialDto> findAllActive() {
        return materialRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(MaterialDto userDto) {
        MaterialEntity savingObj = modelMapper.map(userDto, MaterialEntity.class);
        materialRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        materialRepo.delete(materialRepo.findById(id));
    }

    @Override
    public List<MaterialDto> findBySubgroupId(int id) {
        return materialRepo.findBySubgroupId(id).stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public MaterialDto findByCode(String code) {
        return entity2Dto(materialRepo.findByCode(code));
    }

    private MaterialDto entity2Dto(MaterialEntity entity){
        return modelMapper.map(entity, MaterialDto.class);
    }
}
