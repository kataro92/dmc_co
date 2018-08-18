package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.DmcWarehouseEntity;
import com.kat.dmc.common.model.WarehouseDto;
import com.kat.dmc.repository.interfaces.*;
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
    WarehouseRepo warehouseRepo;

    @Autowired
    MaterialImportDetailRepo importDetailRepo;
    @Autowired
    MaterialExportDetailRepo exportDetailRepo;
    @Autowired
    MaterialImportRepo importRepo;
    @Autowired
    MaterialExportRepo exportRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<WarehouseDto> findAll() {
        return warehouseRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<WarehouseDto> findAllActive() {
        return warehouseRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<WarehouseDto> findAllActiveWithStatus() {
        return warehouseRepo.findAllActive().stream().map(this::getWarehouseStatus).collect(Collectors.toList());
    }

    @Override
    public List<WarehouseDto> findAllActiveByPermission(Boolean canImport, Boolean canExport, Boolean canTransfer, Boolean canDismiss) {
        return warehouseRepo.findAllActiveByPermission(canImport, canExport, canTransfer, canDismiss).stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(WarehouseDto userDto) {
        DmcWarehouseEntity savingObj = modelMapper.map(userDto, DmcWarehouseEntity.class);
        warehouseRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        warehouseRepo.delete(warehouseRepo.findById(id));
    }

    private WarehouseDto getWarehouseStatus(DmcWarehouseEntity warehouseEntity) {
        WarehouseDto warehouseDto = entity2Dto(warehouseEntity);
        warehouseDto.setTotalImportedMaterial(importDetailRepo.countQuantityByWarehouseId(warehouseEntity.getId()));
        warehouseDto.setTotalExportedMaterial(exportDetailRepo.countQuantityByWarehouseId(warehouseEntity.getId()));
        warehouseDto.setTotalImportedPaper(importRepo.countQuantityByWarehouseId(warehouseEntity.getId()));
        warehouseDto.setTotalExportedPaper(exportRepo.countQuantityByWarehouseId(warehouseEntity.getId()));
        warehouseDto.setCurrentMaterial(warehouseDto.getTotalImportedMaterial() - warehouseDto.getTotalExportedMaterial());
        return warehouseDto;
    }

    private WarehouseDto entity2Dto(DmcWarehouseEntity entity){
        return modelMapper.map(entity, WarehouseDto.class);
    }
}
