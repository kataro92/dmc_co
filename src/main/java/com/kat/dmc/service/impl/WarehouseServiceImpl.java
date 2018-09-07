package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.dto.MaterialOnStockDto;
import com.kat.dmc.common.dto.WarehouseDto;
import com.kat.dmc.common.model.*;
import com.kat.dmc.common.req.WarehouseSearchReq;
import com.kat.dmc.repository.interfaces.*;
import com.kat.dmc.service.interfaces.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public List<MaterialOnStockDto> findAllMaterialOnStock() {
        //Get all import material
        List<DmcMaterialImportDetailEntity> importEntities = importDetailRepo.findAllActive();
        List<DmcMaterialImportEntity> importEntityList = importRepo.findAllActive();
        List<MaterialOnStockDto> materialOnStockDtoList = new ArrayList<>();
        for(DmcMaterialImportDetailEntity importDetailEntity : importEntities){
            boolean isMeet = false;
            for(MaterialOnStockDto materialOnStockDto : materialOnStockDtoList){
                if(materialOnStockDto.getMaterialId() == importDetailEntity.getMaterialId()
                        && materialOnStockDto.getImportId() == importDetailEntity.getMaterialImportId()){
                    isMeet = true;
                    materialOnStockDto.setMaterialQuantity(materialOnStockDto.getMaterialQuantity() + importDetailEntity.getQuantity());
                }
            }
            if(!isMeet){
                MaterialOnStockDto materialOnStockDto = new MaterialOnStockDto();
                materialOnStockDto.setImportId(importDetailEntity.getMaterialImportId());
                materialOnStockDto.setMaterialId(importDetailEntity.getMaterialId());
                materialOnStockDto.setMaterialPrice(importDetailEntity.getPrice());
                materialOnStockDto.setMaterialQuantity(importDetailEntity.getQuantity());
                materialOnStockDto.setMaterialGroupId(importDetailEntity.getMaterialGroupId());
                for(DmcMaterialImportEntity importEntity : importEntityList){
                    if(importEntity.getId() == importDetailEntity.getMaterialImportId()){
                        materialOnStockDto.setWarehouseId(importEntity.getWarehouseId());
                    }
                }
                materialOnStockDtoList.add(materialOnStockDto);
            }
        }
        return materialOnStockDtoList;
    }

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

    @Override
    public List<MaterialIETDDto> findAllBySearchReq(WarehouseSearchReq sumOnStockReq) {
        //SELECT
        return null;
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
