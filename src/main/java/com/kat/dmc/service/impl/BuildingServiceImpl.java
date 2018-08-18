package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.*;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.repository.interfaces.BuildingMaterialRepo;
import com.kat.dmc.repository.interfaces.BuildingProductRepo;
import com.kat.dmc.repository.interfaces.MaterialImportRepo;
import com.kat.dmc.service.interfaces.BuildingService;
import com.kat.dmc.service.interfaces.MaterialService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingProductRepo buildingProductRepo;
    @Autowired
    BuildingMaterialRepo buildingMaterialRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MaterialService materialService;
    @Autowired
    MaterialImportRepo materialImportRepo;

    @PostConstruct
    public void init(){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public List<BuildingProductDto> findAll() {
        List<DmcBuildingProductEntity> buildingProductEntities = buildingProductRepo.findAll();
        List<DmcBuildingMaterialEntity> dmcBuildingMaterialEntities = buildingMaterialRepo.findAll();
        List<BuildingProductDto> buildingProductDtos = new ArrayList();
        for(DmcBuildingProductEntity buildingProductEntity : buildingProductEntities){
            BuildingProductDto buildingProductDto = mapBuildingEntity2Dto(buildingProductEntity);
            List<BuildingMaterialDto> buildingMaterialDtos = new ArrayList<>();
            for(DmcBuildingMaterialEntity buildingMaterialEntity : dmcBuildingMaterialEntities){
                if(buildingMaterialEntity.getBuildingProductId().equals(buildingProductEntity.getId())) {
                    buildingMaterialDtos.add(mapMaterialEntity2Dto(buildingMaterialEntity));
                }
            }
            buildingProductDto.setBuildingMaterialDtos(buildingMaterialDtos);
            buildingProductDtos.add(buildingProductDto);
        }
        return buildingProductDtos;
    }

    private BuildingMaterialDto mapMaterialEntity2Dto(DmcBuildingMaterialEntity buildingMaterialEntity) {
        return modelMapper.map(buildingMaterialEntity, BuildingMaterialDto.class);
    }

    private BuildingProductDto mapBuildingEntity2Dto(DmcBuildingProductEntity buildingProductEntity) {
        return modelMapper.map(buildingProductEntity, BuildingProductDto.class);
    }

    @Override
    public void delete(int buildingProductId) {

    }

    @Override
    public void save(BuildingProductDto buildingProductDto) throws Exception {
        //Check quantity for each material
        for(BuildingMaterialDto buildingMaterialDto : buildingProductDto.getBuildingMaterialDtos()) {
            List<DmcMaterialImportDetailEntity> materialList = materialImportRepo.findAllActiveByMaterialIdImpIds(buildingMaterialDto.getImportId(), buildingMaterialDto.getMaterialId());
            long countAllQuantity = 0L;
            for(DmcMaterialImportDetailEntity materialImportDetailEntity : materialList){
                countAllQuantity += materialImportDetailEntity.getQuantity();
            }
            if(countAllQuantity < buildingMaterialDto.getQuantity()){
                throw new Exception("Nguyên liệu không đủ để chế tạo sản phẩm ["+buildingMaterialDto.getMaterialName()+"]");
            }
        }
        //Create export material
        //TO-DO làm nốt phần tự động tạo phiếu xuất kho
        //Create new building product
        DmcBuildingProductEntity buildingProductEntity = modelMapper.map(buildingProductDto, DmcBuildingProductEntity.class);
        buildingProductEntity = buildingProductRepo.save(buildingProductEntity);
        //Create new building material
        for(BuildingMaterialDto buildingMaterialDto : buildingProductDto.getBuildingMaterialDtos()){
            DmcBuildingMaterialEntity buildingMaterialEntity = new DmcBuildingMaterialEntity();
            buildingMaterialEntity.setProductId(buildingMaterialDto.getProductId());
            buildingMaterialEntity.setMaterialId(buildingMaterialDto.getMaterialId());
            buildingMaterialEntity.setMaterialType(buildingMaterialDto.getMaterialType());
            buildingMaterialEntity.setStatus(0);
            buildingMaterialEntity.setQuantity(buildingMaterialDto.getQuantity());
            buildingMaterialEntity.setPrice(buildingMaterialDto.getPrice());
            buildingMaterialEntity.setTotal(buildingMaterialDto.getTotal());
            buildingMaterialEntity.setUsedDate(DateUtil.getCurrentDayTS());
            buildingMaterialEntity.setMaterialCode(buildingMaterialDto.getMaterialCode());
            buildingMaterialEntity.setMaterialName(buildingMaterialDto.getMaterialName());
            buildingMaterialEntity.setBuildingProductId(buildingProductEntity.getId());
            buildingMaterialRepo.save(buildingMaterialEntity);
        }
    }

    @Override
    public List<BuildingProductDto> findProductByWarehouse(int buildingProductId) {
        return null;
    }
}
