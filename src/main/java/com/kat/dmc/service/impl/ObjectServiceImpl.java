package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.DmcObjectEntity;
import com.kat.dmc.common.model.DmcUserObjectEntity;
import com.kat.dmc.common.model.ObjectDto;
import com.kat.dmc.repository.interfaces.ObjectRepo;
import com.kat.dmc.service.interfaces.ObjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    ObjectRepo objectRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ObjectDto> findAll() {
        List<DmcObjectEntity> lstAllObject = objectRepo.findAll();
        List<ObjectDto> lstReturn = new ArrayList<>();
        for(DmcObjectEntity objectEntity : lstAllObject){
            if(objectEntity.getParentObjectId() == 0){
                ObjectDto firstChild = modelMapper.map(objectEntity, ObjectDto.class);
                mapRescusiveChild(firstChild, lstAllObject);
                lstReturn.add(firstChild);
            }
        }
        return lstReturn;
    }

    private void mapRescusiveChild(ObjectDto parent, List<DmcObjectEntity> lstAllObject){
        for(DmcObjectEntity objectEntity : lstAllObject){
            if(objectEntity.getParentObjectId() == parent.getObjectId()){
                if(parent.getLstChildObject() == null){
                    parent.setLstChildObject(new ArrayList<>());
                }
                ObjectDto child = modelMapper.map(objectEntity, ObjectDto.class);
                mapRescusiveChild(child, lstAllObject);
                parent.getLstChildObject().add(child);
            }
        }
    }

    @Override
    public void save(ObjectDto userDto) {
        DmcObjectEntity savingObj = modelMapper.map(userDto, DmcObjectEntity.class);
        objectRepo.save(savingObj);
    }

    @Override
    public void delete(Integer objId) {
        DmcObjectEntity deletingObj = objectRepo.findById(objId);
        objectRepo.delete(deletingObj);
    }

    @Override
    public void saveNewPermission(List<ObjectDto> lstNewPermission, Integer userId) {
        if(lstNewPermission != null && !lstNewPermission.isEmpty()){
            objectRepo.removeAllPermissionByUserID(userId);
            for(ObjectDto userDto : lstNewPermission){
                DmcUserObjectEntity savingObj = new DmcUserObjectEntity();
                savingObj.setObjectId(userDto.getObjectId());
                savingObj.setUserId(userId);
                objectRepo.saveUserObject(savingObj);
            }
        }else{
            objectRepo.removeAllPermissionByUserID(userId);
        }
    }

    @Override
    public List<Integer> findObjectIdByUserId(Integer userId) {
        return objectRepo.findObjectIdByUserId(userId);
    }
}
