package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.MaterialDto;
import com.kat.dmc.common.dto.MaterialImportDetailDto;
import com.kat.dmc.common.model.DmcMaterialEntity;
import com.kat.dmc.repository.interfaces.MaterialRepo;
import com.kat.dmc.service.interfaces.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        DmcMaterialEntity savingObj = modelMapper.map(userDto, DmcMaterialEntity.class);
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

    @Override
    public List<MaterialDto> findAllByImport() {
        List<MaterialDto> dtoList = materialRepo.findAllByImport();
        List<MaterialDto> returnList = new ArrayList<>();
        for(MaterialDto detailDto : dtoList){
            //Get import_id fit with price
            List<MaterialImportDetailDto> importIds = materialRepo.findImpIdsByMaterialId(detailDto.getId());
            for(MaterialImportDetailDto materialImportDetailDto : importIds){
                MaterialDto newImp = detailDto.clone();
                newImp.setCurrentPrice(materialImportDetailDto.getPrice());
                newImp.setCurrentImportId(materialImportDetailDto.getMaterialImportId());
                newImp.setCurrentImportCode(materialImportDetailDto.getCode());
                newImp.setUnit(materialImportDetailDto.getUnit());
                returnList.add(newImp);
            }

        }
        return returnList;
    }

    @Override
    public MaterialDto findByImport(int materialId, int importId) {
        DmcMaterialEntity dmcMaterialEntity = materialRepo.findById(materialId);
        MaterialDto detailDto = modelMapper.map(dmcMaterialEntity, MaterialDto.class);
        List<MaterialImportDetailDto> importIds = materialRepo.findImpIdsByMaterialId(detailDto.getId());
        for(MaterialImportDetailDto materialImportDetailDto : importIds){
            if(materialImportDetailDto.getId() == importId) {
                MaterialDto newImp = detailDto.clone();
                newImp.setCurrentPrice(materialImportDetailDto.getPrice());
                newImp.setCurrentImportId(materialImportDetailDto.getMaterialImportId());
                newImp.setCurrentImportCode(materialImportDetailDto.getCode());
                newImp.setCurrentImportId(materialImportDetailDto.getMaterialImportId());
                newImp.setUnit(materialImportDetailDto.getUnit());
                return newImp;
            }
        }
        return null;
    }


    private MaterialDto entity2Dto(DmcMaterialEntity entity){
        return modelMapper.map(entity, MaterialDto.class);
    }
}
