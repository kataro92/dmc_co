package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.DmcWarehouseEntity;
import com.kat.dmc.common.model.WarehouseDto;
import com.kat.dmc.repository.interfaces.WarehouseRepo;
import com.kat.dmc.service.interfaces.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    WarehouseRepo jobPositionRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<WarehouseDto> findAll() {
        return jobPositionRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<WarehouseDto> findAllActive() {
        return jobPositionRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<WarehouseDto> findAllActiveByPermission(Boolean canImport, Boolean canExport, Boolean canTransfer, Boolean canDismiss) {
        return jobPositionRepo.findAllActiveByPermission(canImport, canExport, canTransfer, canDismiss).stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(WarehouseDto userDto) {
        DmcWarehouseEntity savingObj = modelMapper.map(userDto, DmcWarehouseEntity.class);
        jobPositionRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        jobPositionRepo.delete(jobPositionRepo.findById(id));
    }

    private WarehouseDto entity2Dto(DmcWarehouseEntity entity){
        return modelMapper.map(entity, WarehouseDto.class);
    }
}
