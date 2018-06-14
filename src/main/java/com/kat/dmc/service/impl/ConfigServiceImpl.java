package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.ConfigDto;
import com.kat.dmc.common.model.DmcConfigEntity;
import com.kat.dmc.repository.interfaces.ConfigRepo;
import com.kat.dmc.service.interfaces.ConfigService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigRepo jobPositionRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ConfigDto> findAll() {
        return jobPositionRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(ConfigDto userDto) {
        DmcConfigEntity savingObj = modelMapper.map(userDto, DmcConfigEntity.class);
        jobPositionRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        jobPositionRepo.delete(jobPositionRepo.findById(id));
    }

    private ConfigDto entity2Dto(DmcConfigEntity entity){
        return modelMapper.map(entity, ConfigDto.class);
    }
}
