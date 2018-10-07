package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.*;
import com.kat.dmc.common.model.DmcBuildingMaterialEntity;
import com.kat.dmc.common.model.DmcBuildingProductEntity;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.repository.interfaces.BuildingMaterialRepo;
import com.kat.dmc.repository.interfaces.BuildingProductRepo;
import com.kat.dmc.repository.interfaces.MaterialImportRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    UtilRepo utilRepo;
    @Autowired
    WarehouseImportService warehouseImportService;
    @Autowired
    WarehouseExportService warehouseExportService;
    @Autowired
    WarehouseService warehouseService;

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

    private MaterialImportDto makeImportData(BuildingProductDto buildingProductDto){
        //Create import paper
        MaterialImportDto selectedWarehouseImport = new MaterialImportDto();
        selectedWarehouseImport.setImportDate(DateUtil.getCurrentDayTS());
        selectedWarehouseImport.setId(utilRepo.findSequenceNextval("dmc_material_import_id_seq"));
        selectedWarehouseImport.setCode("NK" + String.format("%06d", selectedWarehouseImport.getId()));
        selectedWarehouseImport.setImportFrom(3);
        selectedWarehouseImport.setStatus(1);
        selectedWarehouseImport.setCategoryId(1);
        selectedWarehouseImport.setWarehouseId(buildingProductDto.getWarehouseId());
        Long total = 0L;
        Integer totalQuantity = 0;
        List<MaterialImportDetailDto> buildingMaterialDtoList = new ArrayList<>();
        for(BuildingMaterialDto buildingMaterialDto : buildingProductDto.getBuildingMaterialDtos()){
            total += buildingMaterialDto.getTotal();
        }

        MaterialImportDetailDto importDetailDto = new MaterialImportDetailDto();
        importDetailDto.setStatus(1);
        importDetailDto.setProductId(buildingProductDto.getProductId());
        importDetailDto.setProductGroupId(buildingProductDto.getProductGroupId());
        importDetailDto.setQuantity(1);
        importDetailDto.setId(utilRepo.findSequenceNextval("dmc_material_import_detail_id_seq"));
        importDetailDto.setCode("NT" + String.format("%06d", importDetailDto.getId()));
        importDetailDto.setImportDate(selectedWarehouseImport.getImportDate());
        importDetailDto.setTotal(total);
        importDetailDto.setPrice(total.intValue());
        buildingMaterialDtoList.add(importDetailDto);

        selectedWarehouseImport.setLstDetails(buildingMaterialDtoList);
        selectedWarehouseImport.setTotal(total);
        selectedWarehouseImport.setChildQuantity(totalQuantity);
        return selectedWarehouseImport;
    }

    private List<MaterialExportDto> makeExportDataList(BuildingProductDto buildingProductDto, List<MaterialOnStockDto> materialOnStock){
        Integer oldImportId = 0;
        Integer curentI = 0;
        List<MaterialExportDto> materialExportDtoList = new ArrayList<>();
        for(BuildingMaterialDto buildingMaterialDto : buildingProductDto.getBuildingMaterialDtos()){
            for(MaterialOnStockDto materialOnStockDto : materialOnStock){
                if(materialOnStockDto.getMaterialId() == buildingMaterialDto.getMaterialId()
                        && buildingMaterialDto.getQuantity() > 0 && materialOnStockDto.getMaterialQuantity() > 0){
                    //Create export detail
                    MaterialExportDetailDto materialExportDetailDto = new MaterialExportDetailDto();
                    materialExportDetailDto.setId(utilRepo.findSequenceNextval("dmc_material_export_detail_id_seq"));
                    materialExportDetailDto.setCode("XT" + String.format("%06d", materialExportDetailDto.getId()));
                    materialExportDetailDto.setExportDate(buildingProductDto.getCreatedDate());
                    materialExportDetailDto.setMaterialId(materialOnStockDto.getMaterialId());
                    materialExportDetailDto.setMaterialGroupId(materialOnStockDto.getMaterialGroupId());
                    materialExportDetailDto.setPrice(materialOnStockDto.getMaterialPrice());
                    materialExportDetailDto.setStatus(1);
                    if(buildingMaterialDto.getQuantity() > materialOnStockDto.getMaterialQuantity()){
                        materialExportDetailDto.setQuantity(materialOnStockDto.getMaterialQuantity().intValue());
                        buildingMaterialDto.setQuantity(buildingMaterialDto.getQuantity().intValue() - materialOnStockDto.getMaterialQuantity().intValue());
                        materialOnStockDto.setMaterialQuantity(0);
                    }else if (buildingMaterialDto.getQuantity() < materialOnStockDto.getMaterialQuantity()){
                        materialExportDetailDto.setQuantity(buildingMaterialDto.getQuantity().intValue());
                        materialOnStockDto.setMaterialQuantity(materialOnStockDto.getMaterialQuantity().intValue() - buildingMaterialDto.getQuantity().intValue());
                        buildingMaterialDto.setQuantity(0);
                    }else{
                        materialExportDetailDto.setQuantity(buildingMaterialDto.getQuantity().intValue());
                        buildingMaterialDto.setQuantity(0);
                        materialOnStockDto.setMaterialQuantity(0);
                    }
                    materialExportDetailDto.setTotal(materialExportDetailDto.getQuantity() * materialExportDetailDto.getPrice().longValue());
                    if(oldImportId != materialOnStockDto.getImportId()){
                        MaterialExportDto selectedWarehouseExport = new MaterialExportDto();
                        selectedWarehouseExport.setExportDate(DateUtil.getCurrentDayTS());
                        selectedWarehouseExport.setId(utilRepo.findSequenceNextval("dmc_material_export_id_seq"));
                        selectedWarehouseExport.setCode("XK" + String.format("%06d", selectedWarehouseExport.getId()));
                        selectedWarehouseExport.setExportFrom(3);
                        selectedWarehouseExport.setExportFromId(buildingProductDto.getWarehouseId());
                        selectedWarehouseExport.setCategoryId(0);
                        selectedWarehouseExport.setStatus(1);
                        curentI++;
                        oldImportId = materialOnStockDto.getImportId();
                        List<MaterialExportDetailDto> materialExportDtos = new ArrayList<>();
                        materialExportDetailDto.setMaterialExportId(selectedWarehouseExport.getId());
                        materialExportDtos.add(materialExportDetailDto.clone());
                        selectedWarehouseExport.setLstDetails(materialExportDtos);
                        materialExportDtoList.add(selectedWarehouseExport);
                        selectedWarehouseExport.setTotal(materialExportDetailDto.getTotal());
                    }else{
                        MaterialExportDto selectedWarehouseExport = materialExportDtoList.get(curentI - 1);
                        materialExportDetailDto.setMaterialExportId(selectedWarehouseExport.getId());
                        selectedWarehouseExport.getLstDetails().add(materialExportDetailDto.clone());
                        selectedWarehouseExport.setTotal(selectedWarehouseExport.getTotal() + materialExportDetailDto.getTotal());

                    }
                }
            }
        }
        return materialExportDtoList;
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
        warehouseImportService.save(makeImportData(buildingProductDto));
        //Create new building product
        DmcBuildingProductEntity buildingProductEntity = modelMapper.map(buildingProductDto, DmcBuildingProductEntity.class);
        buildingProductEntity = buildingProductRepo.save(buildingProductEntity);
        //Create new building material
        for(BuildingMaterialDto buildingMaterialDto : buildingProductDto.getBuildingMaterialDtos()){
            buildingMaterialRepo.save(buidingDto2Entity(buildingMaterialDto, buildingProductEntity.getId()));
        }
        //Create export material
        //Tìm các nguyên liệu từ kho nào
        List<MaterialOnStockDto> materialOnStock = warehouseService.findAllMaterialOnStock();
        for(MaterialExportDto materialExportDto : makeExportDataList(buildingProductDto, materialOnStock)){
            warehouseExportService.save(materialExportDto);
        }
    }

    private DmcBuildingMaterialEntity buidingDto2Entity(BuildingMaterialDto buildingMaterialDto, Integer buildingProductId){
        DmcBuildingMaterialEntity buildingMaterialEntity = new DmcBuildingMaterialEntity();
        buildingMaterialEntity.setProductId(buildingMaterialDto.getProductId());
        buildingMaterialEntity.setMaterialId(buildingMaterialDto.getMaterialId());
        buildingMaterialEntity.setMaterialType(buildingMaterialDto.getMaterialType());
        buildingMaterialEntity.setStatus(buildingMaterialDto.getStatus());
        buildingMaterialEntity.setQuantity(buildingMaterialDto.getQuantity());
        buildingMaterialEntity.setPrice(buildingMaterialDto.getPrice());
        buildingMaterialEntity.setTotal(buildingMaterialDto.getTotal());
        buildingMaterialEntity.setUsedDate(DateUtil.getCurrentDayTS());
        buildingMaterialEntity.setMaterialCode(buildingMaterialDto.getMaterialCode());
        buildingMaterialEntity.setMaterialName(buildingMaterialDto.getMaterialName());
        buildingMaterialEntity.setBuildingProductId(buildingProductId);
        buildingMaterialEntity.setImportId(buildingMaterialDto.getBuildingImportId());
        return buildingMaterialEntity;
    }

    private MaterialImportDetailDto materialImportDetailEntity2Dto(DmcBuildingMaterialEntity buildingMaterialEntity){
        MaterialImportDetailDto materialImportDetailDto = new MaterialImportDetailDto();
        materialImportDetailDto.setId(buildingMaterialEntity.getId());
        materialImportDetailDto.setMaterialImportId(buildingMaterialEntity.getImportId());
        materialImportDetailDto.setCode(buildingMaterialEntity.getMaterialCode());
        materialImportDetailDto.setPrice(buildingMaterialEntity.getPrice());
        materialImportDetailDto.setQuantity(buildingMaterialEntity.getQuantity());
        materialImportDetailDto.setTotal(buildingMaterialEntity.getTotal());
        materialImportDetailDto.setMaterialId(buildingMaterialEntity.getMaterialId());
        materialImportDetailDto.setImportDate(buildingMaterialEntity.getUsedDate());
        materialImportDetailDto.setStatus(buildingMaterialEntity.getStatus());
        return materialImportDetailDto;
    }

    @Override
    public List<BuildingProductDto> findProductByWarehouse(int buildingProductId) {
        return null;
    }

    @Override
    public List<MaterialImportDetailDto> findAllActiveDtlByMaterialImpId(int importId) {
        List<DmcBuildingMaterialEntity> materialEntities = buildingMaterialRepo.findByImportId(importId);
        return materialEntities.stream().map(this::materialImportDetailEntity2Dto).collect(Collectors.toList());
    }
}
